package com.lvwyh.service.impl;

import com.lvwyh.entity.*;
import com.lvwyh.mapper.*;
import com.lvwyh.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {

    private static final String SQLITE_PREFIX = "jdbc:sqlite:";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("无法加载SQLite驱动", e);
        }
    }

    @Autowired
    private StateTypeMapper stateTypeMapper;
    @Autowired
    private WarshipTypeMapper warshipTypeMapper;
    @Autowired
    private WeaponTypeMapper weaponTypeMapper;
    @Autowired
    private ExpendableTypeMapper expendableTypeMapper;
    @Autowired
    private ArmorLevelMapper armorLevelMapper;
    @Autowired
    private MobilityLevelMapper mobilityLevelMapper;
    @Autowired
    private ConcealmentLevelMapper concealmentLevelMapper;
    @Autowired
    private WeaponArchiveMapper weaponArchiveMapper;

    @Override
    public byte[] exportConfigDatabase() {
        Path tempFile = null;
        try {
            tempFile = Files.createTempFile("trpg-config-", ".db");
            try (Connection conn = DriverManager.getConnection(SQLITE_PREFIX + tempFile.toAbsolutePath())) {
                conn.setAutoCommit(false);
                try {
                    createTables(conn);
                    insertStateTypes(conn, stateTypeMapper.selectAll());
                    insertWarshipTypes(conn, warshipTypeMapper.selectAll());
                    insertWeaponTypes(conn, weaponTypeMapper.selectAll());
                    insertExpendableTypes(conn, expendableTypeMapper.selectAll());
                    insertArmorLevels(conn, armorLevelMapper.selectAll());
                    insertMobilityLevels(conn, mobilityLevelMapper.selectAll());
                    insertConcealmentLevels(conn, concealmentLevelMapper.selectAll());
                    insertWeaponArchives(conn, weaponArchiveMapper.selectAll());
                    conn.commit();
                } catch (Exception ex) {
                    conn.rollback();
                    throw ex;
                }
            }
            return Files.readAllBytes(tempFile);
        } catch (IOException | SQLException ex) {
            throw new RuntimeException("导出配置数据失败", ex);
        } finally {
            if (tempFile != null) {
                try {
                    Files.deleteIfExists(tempFile);
                } catch (IOException ignore) {
                }
            }
        }
    }

    private void createTables(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS state_type (" +
                    "id INTEGER PRIMARY KEY, " +
                    "state_type_id INTEGER NOT NULL, " +
                    "state_type_name TEXT NOT NULL, " +
                    "weapon_types TEXT"
                    + ")");
            stmt.execute("CREATE TABLE IF NOT EXISTS warship_type (" +
                    "id INTEGER PRIMARY KEY, " +
                    "warship_type_id INTEGER NOT NULL, " +
                    "warship_type_name TEXT NOT NULL, " +
                    "armor INTEGER NOT NULL, " +
                    "HP INTEGER NOT NULL, " +
                    "mobility INTEGER NOT NULL, " +
                    "concealment INTEGER NOT NULL, " +
                    "action_priority INTEGER NOT NULL, " +
                    "weapon_types TEXT"
                    + ")");
            stmt.execute("CREATE TABLE IF NOT EXISTS weapon_type (" +
                    "id INTEGER PRIMARY KEY, " +
                    "weapon_type_id INTEGER NOT NULL, " +
                    "weapon_type_name TEXT NOT NULL, " +
                    "level INTEGER NOT NULL, " +
                    "parent_id INTEGER, " +
                    "description TEXT"
                    + ")");
            stmt.execute("CREATE TABLE IF NOT EXISTS expendable_type (" +
                    "id INTEGER PRIMARY KEY, " +
                    "expendable_type_id INTEGER NOT NULL, " +
                    "expendable_type_name TEXT NOT NULL, " +
                    "level INTEGER, " +
                    "parent_id INTEGER, " +
                    "description TEXT"
                    + ")");
            stmt.execute("CREATE TABLE IF NOT EXISTS armor_level (" +
                    "id INTEGER PRIMARY KEY, " +
                    "level INTEGER NOT NULL, " +
                    "name TEXT NOT NULL, " +
                    "start_num INTEGER NOT NULL, " +
                    "end_num INTEGER NOT NULL"
                    + ")");
            stmt.execute("CREATE TABLE IF NOT EXISTS mobility_level (" +
                    "id INTEGER PRIMARY KEY, " +
                    "level INTEGER NOT NULL, " +
                    "distance INTEGER NOT NULL, " +
                    "is_diagonal INTEGER, " +
                    "is_avoiding_torpedoes INTEGER, " +
                    "avoiding_roll TEXT, " +
                    "avoiding_less INTEGER"
                    + ")");
            stmt.execute("CREATE TABLE IF NOT EXISTS concealment_level (" +
                    "id INTEGER PRIMARY KEY, " +
                    "level INTEGER NOT NULL, " +
                    "discover_radius INTEGER NOT NULL, " +
                    "air_discover_radius INTEGER NOT NULL"
                    + ")");
            stmt.execute("CREATE TABLE IF NOT EXISTS weapon_archive (" +
                    "id INTEGER PRIMARY KEY, " +
                    "state_type_id INTEGER NOT NULL, " +
                    "weapon_type_id_1 INTEGER, " +
                    "weapon_type_id_2 INTEGER, " +
                    "weapon_model_id INTEGER NOT NULL, " +
                    "weapon_model_name TEXT NOT NULL, " +
                    "skill_points INTEGER, " +
                    "cd_n INTEGER, " +
                    "cd_x INTEGER, " +
                    "link_num INTEGER, " +
                    "is_dual_use INTEGER, " +
                    "action_radius INTEGER, " +
                    "ammunition_speed INTEGER, " +
                    "precision TEXT, " +
                    "ignition_rate REAL, " +
                    "leakage_rate REAL, " +
                    "damage INTEGER, " +
                    "HE_damage INTEGER, " +
                    "AP_damage INTEGER, " +
                    "SAP_damage INTEGER, " +
                    "damage_range INTEGER, " +
                    "penetration_level_start INTEGER, " +
                    "penetration_level_end INTEGER, " +
                    "core_breakdown REAL, " +
                    "half_breakdown REAL, " +
                    "pass_breakdown REAL, " +
                    "size INTEGER, " +
                    "payload INTEGER, " +
                    "HP INTEGER, " +
                    "move_speed INTEGER, " +
                    "hangar INTEGER, " +
                    "recovery_speed_N INTEGER, " +
                    "recovery_speed_X INTEGER, " +
                    "arrival_time INTEGER, " +
                    "distance_before_attack INTEGER, " +
                    "description TEXT, " +
                    "weapon_type_id_3 INTEGER, " +
                    "weapon_type_id_4 INTEGER, " +
                    "weapon_type_id_5 INTEGER"
                    + ")");
        }
    }

    private void insertStateTypes(Connection conn, List<StateType> list) throws SQLException {
        if (list == null || list.isEmpty()) return;
        String sql = "INSERT INTO state_type (id, state_type_id, state_type_name, weapon_types) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (StateType item : list) {
                setLong(ps, 1, item.getId());
                setInteger(ps, 2, item.getStateTypeId());
                setString(ps, 3, item.getStateTypeName());
                setString(ps, 4, item.getWeaponTypes());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertWarshipTypes(Connection conn, List<WarshipType> list) throws SQLException {
        if (list == null || list.isEmpty()) return;
        String sql = "INSERT INTO warship_type (id, warship_type_id, warship_type_name, armor, HP, mobility, concealment, action_priority, weapon_types) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (WarshipType item : list) {
                setLong(ps, 1, item.getId());
                setInteger(ps, 2, item.getWarshipTypeId());
                setString(ps, 3, item.getWarshipTypeName());
                setInteger(ps, 4, item.getArmor());
                setInteger(ps, 5, item.getHp());
                setInteger(ps, 6, item.getMobility());
                setInteger(ps, 7, item.getConcealment());
                setInteger(ps, 8, item.getActionPriority());
                setString(ps, 9, item.getWeaponTypes());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertWeaponTypes(Connection conn, List<WeaponType> list) throws SQLException {
        if (list == null || list.isEmpty()) return;
        String sql = "INSERT INTO weapon_type (id, weapon_type_id, weapon_type_name, level, parent_id, description) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (WeaponType item : list) {
                setLong(ps, 1, item.getId());
                setInteger(ps, 2, item.getWeaponTypeId());
                setString(ps, 3, item.getWeaponTypeName());
                setInteger(ps, 4, item.getLevel());
                setInteger(ps, 5, item.getParentId());
                setString(ps, 6, item.getDescription());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertExpendableTypes(Connection conn, List<ExpendableType> list) throws SQLException {
        if (list == null || list.isEmpty()) return;
        String sql = "INSERT INTO expendable_type (id, expendable_type_id, expendable_type_name, level, parent_id, description) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (ExpendableType item : list) {
                setLong(ps, 1, item.getId());
                setInteger(ps, 2, item.getExpendableTypeId());
                setString(ps, 3, item.getExpendableTypeName());
                setInteger(ps, 4, item.getLevel());
                setInteger(ps, 5, item.getParentId());
                setString(ps, 6, item.getDescription());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertArmorLevels(Connection conn, List<ArmorLevel> list) throws SQLException {
        if (list == null || list.isEmpty()) return;
        String sql = "INSERT INTO armor_level (id, level, name, start_num, end_num) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (ArmorLevel item : list) {
                setLong(ps, 1, item.getId());
                setInteger(ps, 2, item.getLevel());
                setString(ps, 3, item.getName());
                setInteger(ps, 4, item.getStartNum());
                setInteger(ps, 5, item.getEndNum());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertMobilityLevels(Connection conn, List<MobilityLevel> list) throws SQLException {
        if (list == null || list.isEmpty()) return;
        String sql = "INSERT INTO mobility_level (id, level, distance, is_diagonal, is_avoiding_torpedoes, avoiding_roll, avoiding_less) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (MobilityLevel item : list) {
                setLong(ps, 1, item.getId());
                setInteger(ps, 2, item.getLevel());
                setInteger(ps, 3, item.getDistance());
                setBooleanAsInt(ps, 4, item.getIsDiagonal());
                setBooleanAsInt(ps, 5, item.getIsAvoidingTorpedoes());
                setString(ps, 6, item.getAvoidingRoll());
                setInteger(ps, 7, item.getAvoidingLess());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertConcealmentLevels(Connection conn, List<ConcealmentLevel> list) throws SQLException {
        if (list == null || list.isEmpty()) return;
        String sql = "INSERT INTO concealment_level (id, level, discover_radius, air_discover_radius) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (ConcealmentLevel item : list) {
                setLong(ps, 1, item.getId());
                setInteger(ps, 2, item.getLevel());
                setInteger(ps, 3, item.getDiscoverRadius());
                setInteger(ps, 4, item.getAirDiscoverRadius());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertWeaponArchives(Connection conn, List<WeaponArchive> list) throws SQLException {
        if (list == null || list.isEmpty()) return;
        String sql = "INSERT INTO weapon_archive (" +
                "id, state_type_id, weapon_type_id_1, weapon_type_id_2, weapon_model_id, weapon_model_name, skill_points, cd_n, cd_x, " +
                "link_num, is_dual_use, action_radius, ammunition_speed, precision, ignition_rate, leakage_rate, damage, HE_damage, AP_damage, " +
                "SAP_damage, damage_range, penetration_level_start, penetration_level_end, core_breakdown, half_breakdown, pass_breakdown, " +
                "size, payload, HP, move_speed, hangar, recovery_speed_N, recovery_speed_X, arrival_time, distance_before_attack, description, " +
                "weapon_type_id_3, weapon_type_id_4, weapon_type_id_5) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (WeaponArchive item : list) {
                setLong(ps, 1, item.getId());
                setInteger(ps, 2, item.getStateTypeId());
                setInteger(ps, 3, item.getWeaponTypeId1());
                setInteger(ps, 4, item.getWeaponTypeId2());
                setInteger(ps, 5, item.getWeaponModelId());
                setString(ps, 6, item.getWeaponModelName());
                setInteger(ps, 7, item.getSkillPoints());
                setInteger(ps, 8, item.getCdN());
                setInteger(ps, 9, item.getCdX());
                setInteger(ps, 10, item.getLinkNum());
                setBooleanAsInt(ps, 11, item.getIsDualUse());
                setInteger(ps, 12, item.getActionRadius());
                setInteger(ps, 13, item.getAmmunitionSpeed());
                setString(ps, 14, item.getPrecision());
                setBigDecimal(ps, 15, item.getIgnitionRate());
                setBigDecimal(ps, 16, item.getLeakageRate());
                setInteger(ps, 17, item.getDamage());
                setInteger(ps, 18, item.getHeDamage());
                setInteger(ps, 19, item.getApDamage());
                setInteger(ps, 20, item.getSapDamage());
                setInteger(ps, 21, item.getDamageRange());
                setInteger(ps, 22, item.getPenetrationLevelStart());
                setInteger(ps, 23, item.getPenetrationLevelEnd());
                setBigDecimal(ps, 24, item.getCoreBreakdown());
                setBigDecimal(ps, 25, item.getHalfBreakdown());
                setBigDecimal(ps, 26, item.getPassBreakdown());
                setInteger(ps, 27, item.getSize());
                setInteger(ps, 28, item.getPayload());
                setInteger(ps, 29, item.getHp());
                setInteger(ps, 30, item.getMoveSpeed());
                setInteger(ps, 31, item.getHangar());
                setInteger(ps, 32, item.getRecoverySpeedN());
                setInteger(ps, 33, item.getRecoverySpeedX());
                setInteger(ps, 34, item.getArrivalTime());
                setInteger(ps, 35, item.getDistanceBeforeAttack());
                setString(ps, 36, item.getDescription());
                setInteger(ps, 37, item.getWeaponTypeId3());
                setInteger(ps, 38, item.getWeaponTypeId4());
                setInteger(ps, 39, item.getWeaponTypeId5());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void setLong(PreparedStatement ps, int index, Long value) throws SQLException {
        if (value != null) {
            ps.setLong(index, value);
        } else {
            ps.setNull(index, Types.BIGINT);
        }
    }

    private void setInteger(PreparedStatement ps, int index, Integer value) throws SQLException {
        if (value != null) {
            ps.setInt(index, value);
        } else {
            ps.setNull(index, Types.INTEGER);
        }
    }

    private void setString(PreparedStatement ps, int index, String value) throws SQLException {
        if (value != null) {
            ps.setString(index, value);
        } else {
            ps.setNull(index, Types.VARCHAR);
        }
    }

    private void setBooleanAsInt(PreparedStatement ps, int index, Boolean value) throws SQLException {
        if (value != null) {
            ps.setInt(index, value ? 1 : 0);
        } else {
            ps.setNull(index, Types.INTEGER);
        }
    }

    private void setBigDecimal(PreparedStatement ps, int index, BigDecimal value) throws SQLException {
        if (value != null) {
            ps.setBigDecimal(index, value);
        } else {
            ps.setNull(index, Types.DECIMAL);
        }
    }
}
