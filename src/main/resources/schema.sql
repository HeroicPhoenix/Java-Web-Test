-- Schema definitions for TRPG tables (updated structure)
CREATE TABLE IF NOT EXISTS player (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    player_id VARCHAR(64) NOT NULL UNIQUE,
    player_name VARCHAR(128) NOT NULL,
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS room (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    room_id VARCHAR(64) NOT NULL UNIQUE,
    room_name VARCHAR(128) NOT NULL,
    map_size_x INT NOT NULL,
    map_size_y INT NOT NULL,
    rounds INT NOT NULL DEFAULT 1,
    is_battle TINYINT(1) NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS room_round (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    room_id VARCHAR(64) NOT NULL,
    rounds INT NOT NULL,
    start_time DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS warship (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    room_id VARCHAR(64) NOT NULL,
    warship_id VARCHAR(64) NOT NULL,
    rounds INT NOT NULL,
    armor INT NOT NULL,
    hp INT NOT NULL,
    hp_plus INT NOT NULL,
    mobility INT NOT NULL,
    concealment INT NOT NULL,
    rate DECIMAL(10,2) NOT NULL,
    pos_x INT NOT NULL,
    pos_y INT NOT NULL
);

CREATE TABLE IF NOT EXISTS weapon (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    room_id VARCHAR(64) NOT NULL,
    weapon_id VARCHAR(64) NOT NULL,
    rounds INT NOT NULL,
    pos_x INT NOT NULL,
    pos_y INT NOT NULL
);

CREATE TABLE IF NOT EXISTS projectile (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    room_id VARCHAR(64) NOT NULL,
    projectile_id VARCHAR(64) NOT NULL,
    weapon_type_id VARCHAR(64) NOT NULL,
    rounds INT NOT NULL,
    pos_x INT NOT NULL,
    pos_y INT NOT NULL
);

CREATE TABLE IF NOT EXISTS warship_archive (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    room_id VARCHAR(64) NOT NULL,
    warship_id VARCHAR(64) NOT NULL,
    warship_name VARCHAR(128) NOT NULL,
    description TEXT,
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    armor_base INT NOT NULL,
    armor_add INT NOT NULL,
    hp_base INT NOT NULL,
    hp_add INT NOT NULL,
    hp_plus_base INT NOT NULL,
    hp_plus_add INT NOT NULL,
    mobility_base INT NOT NULL,
    mobility_add INT NOT NULL,
    concealment_base INT NOT NULL,
    concealment_add INT NOT NULL,
    rate DECIMAL(10,2) NOT NULL,
    total_skill_points INT NOT NULL,
    diving_rounds INT NOT NULL
);

CREATE TABLE IF NOT EXISTS skill_archive (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    skill_id VARCHAR(64) NOT NULL UNIQUE,
    skill_name VARCHAR(128) NOT NULL,
    cd_n INT,
    cd_x INT,
    use_number INT,
    description TEXT,
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS room_player (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    room_id VARCHAR(64) NOT NULL,
    player_id VARCHAR(64) NOT NULL,
    entry_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS player_warship (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    player_id VARCHAR(64) NOT NULL,
    warship_id VARCHAR(64) NOT NULL,
    state_id VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS warship_weapon (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    warship_id VARCHAR(64) NOT NULL,
    weapon_id VARCHAR(64) NOT NULL,
    weapon_type_id VARCHAR(64) NOT NULL,
    weapon_num INT NOT NULL
);

CREATE TABLE IF NOT EXISTS warship_expendable (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    warship_id VARCHAR(64) NOT NULL,
    expendable_type_id VARCHAR(64) NOT NULL,
    expendable_num INT NOT NULL
);

CREATE TABLE IF NOT EXISTS warship_skill (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    warship_id VARCHAR(64) NOT NULL,
    skill_id VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS state_type (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    state_type_id VARCHAR(64) NOT NULL UNIQUE,
    state_type_name VARCHAR(128) NOT NULL,
    weapon_types VARCHAR(512)
);

CREATE TABLE IF NOT EXISTS warship_type (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    warship_type_id VARCHAR(64) NOT NULL UNIQUE,
    warship_type_name VARCHAR(128) NOT NULL,
    armor INT NOT NULL,
    hp INT NOT NULL,
    mobility INT NOT NULL,
    concealment INT NOT NULL,
    action_priority INT NOT NULL,
    weapon_types VARCHAR(512)
);

CREATE TABLE IF NOT EXISTS weapon_type (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    weapon_type_id VARCHAR(64) NOT NULL UNIQUE,
    state_type_id VARCHAR(64) NOT NULL,
    weapon_type_name VARCHAR(128) NOT NULL,
    level INT NOT NULL,
    parent_id VARCHAR(64),
    skill_points INT,
    link_num INT,
    cd_n INT,
    cd_x INT,
    he_damage INT,
    ap_damage INT,
    sap_damage INT,
    action_radius INT,
    speed INT,
    penetration_level_start INT,
    penetration_level_end INT,
    core_breakdown DECIMAL(10,2),
    half_breakdown DECIMAL(10,2),
    pass_breakdown DECIMAL(10,2),
    `precision` DECIMAL(10,2),
    ignition_rate DECIMAL(10,2),
    leakage_rate DECIMAL(10,2),
    description TEXT
);

CREATE TABLE IF NOT EXISTS expendable_type (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    expendable_type_id VARCHAR(64) NOT NULL UNIQUE,
    expendable_type_name VARCHAR(128) NOT NULL,
    level INT NOT NULL,
    parent_id VARCHAR(64),
    description TEXT
);

CREATE TABLE IF NOT EXISTS armor_level (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    level INT NOT NULL UNIQUE,
    name VARCHAR(128) NOT NULL,
    start_num INT NOT NULL,
    end_num INT NOT NULL
);

CREATE TABLE IF NOT EXISTS mobility_level (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    level INT NOT NULL UNIQUE,
    distance INT NOT NULL,
    is_diagonal TINYINT(1) NOT NULL,
    is_avoiding_torpedoes TINYINT(1) NOT NULL,
    avoiding_roll INT,
    avoiding_less INT
);

CREATE TABLE IF NOT EXISTS concealment_level (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    level INT NOT NULL UNIQUE,
    discover_radius INT NOT NULL,
    air_discover_radius INT NOT NULL
);
