SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `armor_level`;
CREATE TABLE `armor_level`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `level` int NOT NULL COMMENT '装甲等级',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '装甲名称',
  `start_num` int NOT NULL DEFAULT 0 COMMENT '开始值',
  `end_num` int NOT NULL DEFAULT 0 COMMENT '结束值',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_armor_level`(`level` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '装甲等级档案（等级对应数值区间）' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `concealment_level`;
CREATE TABLE `concealment_level`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `level` int NOT NULL COMMENT '隐蔽性等级',
  `discover_radius` int NOT NULL DEFAULT 0 COMMENT '被发现半径',
  `air_discover_radius` int NOT NULL DEFAULT 0 COMMENT '对空被发现半径',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_concealment_level`(`level` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '隐蔽性等级档案' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `expendable_type`;
CREATE TABLE `expendable_type`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `expendable_type_id` int NOT NULL COMMENT '消耗品类型ID（业务ID）',
  `expendable_type_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消耗品类型名称',
  `level` int NULL DEFAULT 1 COMMENT '消耗品类型等级',
  `parent_id` int NULL DEFAULT NULL COMMENT '父级ID（无则NULL）',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '消耗品描述',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_expendable_type_id`(`expendable_type_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '消耗品类型档案' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `mobility_level`;
CREATE TABLE `mobility_level`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `level` int NOT NULL COMMENT '机动性等级',
  `distance` int NOT NULL DEFAULT 0 COMMENT '可移动距离',
  `is_diagonal` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否可斜向移动(0否/1是)',
  `is_avoiding_torpedoes` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否可规避鱼雷(0否/1是)',
  `avoiding_roll` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规避概率_骰子（如1d100）',
  `avoiding_less` int NULL DEFAULT NULL COMMENT '规避概率_小于值',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_mobility_level`(`level` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '机动性等级档案' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `player`;
CREATE TABLE `player`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `player_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '玩家id',
  `player_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '玩家名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `player_id`(`player_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `player_warship`;
CREATE TABLE `player_warship`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `player_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '玩家ID',
  `warship_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '战舰ID',
  `state_id` int NOT NULL COMMENT '国家ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_player`(`player_id` ASC) USING BTREE,
  INDEX `idx_warship`(`warship_id` ASC) USING BTREE,
  INDEX `idx_state`(`state_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '玩家与战舰和国家的绑定关系' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `projectile`;
CREATE TABLE `projectile`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `room_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '房间ID',
  `projectile_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '飞行道具ID',
  `weapon_type_id` int NOT NULL COMMENT '武器类型ID',
  `rounds` int NOT NULL COMMENT '回合数',
  `pos_x` int NOT NULL DEFAULT 0 COMMENT '飞行道具位置X',
  `pos_y` int NOT NULL DEFAULT 0 COMMENT '飞行道具位置Y',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_room_projectile_round`(`room_id` ASC, `projectile_id` ASC, `rounds` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '飞行道具每回合情况（每回合INSERT，不UPDATE）' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `room_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '房间id',
  `room_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '房间名称',
  `map_size_x` int NOT NULL COMMENT '地图尺寸x',
  `map_size_y` int NOT NULL COMMENT '地图尺寸y',
  `rounds` int NOT NULL DEFAULT 1 COMMENT '当前回合数',
  `is_battle` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否战斗状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `room_id`(`room_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `room_player`;
CREATE TABLE `room_player`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `room_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '房间id',
  `player_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '玩家id',
  `entry_time` datetime NOT NULL COMMENT '进入时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `room_round`;
CREATE TABLE `room_round`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `room_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '房间id',
  `rounds` int NOT NULL COMMENT '回合数',
  `start_time` datetime NOT NULL COMMENT '每回合开始时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `skill_archive`;
CREATE TABLE `skill_archive`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `skill_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '技能ID（业务ID，可自定义）',
  `skill_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '技能名称',
  `cd_n` int NOT NULL DEFAULT 0 COMMENT '冷却_每隔N回合',
  `cd_x` int NOT NULL DEFAULT 0 COMMENT '冷却_攻击X次',
  `use_number` int NOT NULL DEFAULT 0 COMMENT '总使用次数上限',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '技能描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_skill_id`(`skill_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '玩家自创技能档案' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `state_type`;
CREATE TABLE `state_type`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `state_type_id` int NOT NULL COMMENT '国家类型ID（业务ID）',
  `state_type_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '国家类型名称',
  `weapon_types` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '国家可选武器（ID列表）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_state_type_id`(`state_type_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '国家类型档案' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `warship`;
CREATE TABLE `warship`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `room_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '房间ID',
  `warship_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '战舰ID',
  `rounds` int NOT NULL COMMENT '回合数（从1开始）',
  `armor` int NOT NULL DEFAULT 0 COMMENT '装甲值',
  `HP` int NOT NULL DEFAULT 0 COMMENT '血量值',
  `HP_plus` int NOT NULL DEFAULT 0 COMMENT 'HP加成值（额外值）',
  `mobility` int NOT NULL DEFAULT 0 COMMENT '机动性值',
  `concealment` int NOT NULL DEFAULT 0 COMMENT '隐蔽性值',
  `rate` int NOT NULL DEFAULT 0 COMMENT '综合评分',
  `pos_x` int NOT NULL DEFAULT 0 COMMENT '战舰位置X',
  `pos_y` int NOT NULL DEFAULT 0 COMMENT '战舰位置Y',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_room_ship_round`(`room_id` ASC, `warship_id` ASC, `rounds` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '战舰每回合情况（每回合INSERT，不UPDATE）' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `warship_archive`;
CREATE TABLE `warship_archive`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `room_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '房间ID',
  `warship_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '战舰ID',
  `warship_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '战舰名称',
  `warship_type_id` int NOT NULL COMMENT '战舰类型ID',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '战舰描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `armor_base` int NULL DEFAULT 0 COMMENT '装甲值_基础值',
  `armor_add` int NULL DEFAULT 0 COMMENT '装甲值_加点值',
  `HP_base` int NULL DEFAULT 0 COMMENT '血量值_基础值',
  `HP_add` int NULL DEFAULT 0 COMMENT '血量值_加点值',
  `HP_plus_base` int NULL DEFAULT 0 COMMENT 'HP值_基础值',
  `HP_plus_add` int NULL DEFAULT 0 COMMENT 'HP值_加点值',
  `mobility_base` int NULL DEFAULT 0 COMMENT '机动性值_基础值',
  `mobility_add` int NULL DEFAULT 0 COMMENT '机动性值_加点值',
  `concealment_base` int NULL DEFAULT 0 COMMENT '隐蔽性值_基础值',
  `concealment_add` int NULL DEFAULT 0 COMMENT '隐蔽性值_加点值',
  `rate` int NULL DEFAULT 0 COMMENT '综合评分',
  `total_skill_points` int NULL DEFAULT 0 COMMENT '总技能点数',
  `diving_rounds` int NULL DEFAULT 0 COMMENT '潜艇下潜回合数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '战舰档案（创建时的初始属性）' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `warship_expendable`;
CREATE TABLE `warship_expendable`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `warship_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '战舰ID',
  `expendable_type_id` int NOT NULL COMMENT '消耗品类型ID',
  `expendable_num` int NOT NULL DEFAULT 0 COMMENT '持有数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '战舰与消耗品类型的对应关系' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `warship_skill`;
CREATE TABLE `warship_skill`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `warship_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '战舰ID',
  `skill_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '技能ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '战舰与自创技能的对应关系' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `warship_type`;
CREATE TABLE `warship_type`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `warship_type_id` int NOT NULL COMMENT '战舰类型id',
  `warship_type_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '战舰类型名称',
  `armor` int NOT NULL DEFAULT 0 COMMENT '装甲等级（参考armor_level表）',
  `HP` int NOT NULL DEFAULT 0 COMMENT '血量基础值（耐久）',
  `mobility` int NOT NULL DEFAULT 0 COMMENT '机动性等级（参考mobility_level表）',
  `concealment` int NOT NULL DEFAULT 0 COMMENT '隐蔽性等级（参考concealment_level表）',
  `action_priority` int NOT NULL DEFAULT 0 COMMENT '行动优先级（决定出手顺序）',
  `weapon_types` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '战舰可选武器类型（以逗号分隔的武器类型id列表）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_warship_type_id`(`warship_type_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '战舰类型档案' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `warship_weapon`;
CREATE TABLE `warship_weapon`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `warship_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '战舰ID',
  `weapon_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '武器ID',
  `weapon_type_id` int NOT NULL COMMENT '武器类型ID',
  `weapon_num` int NOT NULL DEFAULT 0 COMMENT '武器数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '战舰与武器的对应关系' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `weapon`;
CREATE TABLE `weapon`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `room_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '房间ID',
  `weapon_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '武器ID',
  `rounds` int NOT NULL COMMENT '回合数',
  `pos_x` int NOT NULL DEFAULT 0 COMMENT '武器位置X',
  `pos_y` int NOT NULL DEFAULT 0 COMMENT '武器位置Y',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_room_weapon_round`(`room_id` ASC, `weapon_id` ASC, `rounds` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '武器每回合情况（每回合INSERT，不UPDATE）' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `weapon_archive`;
CREATE TABLE `weapon_archive`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `state_type_id` int NOT NULL COMMENT '国家类型id',
  `weapon_type_id_1` int NULL DEFAULT NULL COMMENT '武器类型id第1级',
  `weapon_type_id_2` int NULL DEFAULT NULL COMMENT '武器类型id第2级',
  `weapon_model_id` int NOT NULL COMMENT '武器型号id（业务ID）',
  `weapon_model_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '武器型号名称',
  `skill_points` int NOT NULL DEFAULT 0 COMMENT '消耗技能点数',
  `cd_n` int NULL DEFAULT 0 COMMENT '冷却时间_每隔N回合',
  `cd_x` int NULL DEFAULT 0 COMMENT '冷却时间_攻击X次',
  `link_num` int NULL DEFAULT 1 COMMENT '联装数',
  `is_dual_use` tinyint(1) NULL DEFAULT 0 COMMENT '是否高平两用(0否/1是)',
  `action_radius` int NULL DEFAULT 0 COMMENT '射程半径',
  `ammunition_speed` int NULL DEFAULT 0 COMMENT '弹药速度',
  `precision` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '精度系数',
  `ignition_rate` decimal(5, 2) NULL DEFAULT 0.00 COMMENT '点火率(%)',
  `leakage_rate` decimal(5, 2) NULL DEFAULT 0.00 COMMENT '进水率(%)',
  `damage` int NULL DEFAULT 0 COMMENT '标准伤害',
  `HE_damage` int NULL DEFAULT 0 COMMENT '高爆弹_标准伤害',
  `AP_damage` int NULL DEFAULT 0 COMMENT '穿甲弹_标准伤害',
  `SAP_damage` int NULL DEFAULT 0 COMMENT '半穿甲弹_标准伤害',
  `damage_range` int NULL DEFAULT 0 COMMENT '伤害范围',
  `penetration_level_start` int NULL DEFAULT 0 COMMENT '击穿起始等级',
  `penetration_level_end` int NULL DEFAULT 0 COMMENT '击穿结束等级',
  `core_breakdown` decimal(5, 2) NULL DEFAULT 0.00 COMMENT '核心击穿概率(%)',
  `half_breakdown` decimal(5, 2) NULL DEFAULT 0.00 COMMENT '半穿概率(%)',
  `pass_breakdown` decimal(5, 2) NULL DEFAULT 0.00 COMMENT '过穿概率(%)',
  `size` int NULL DEFAULT 0 COMMENT '编队数量/规模',
  `payload` int NULL DEFAULT 0 COMMENT '载弹量',
  `HP` int NULL DEFAULT 0 COMMENT '血量',
  `move_speed` int NULL DEFAULT 0 COMMENT '航速/移动速度',
  `hangar` int NULL DEFAULT 0 COMMENT '机库容量',
  `recovery_speed_N` int NULL DEFAULT 0 COMMENT '机组恢复速度_每隔N回合',
  `recovery_speed_X` int NULL DEFAULT 0 COMMENT '机组恢复速度_恢复X架',
  `arrival_time` int NULL DEFAULT 0 COMMENT '抵达耗时',
  `distance_before_attack` int NULL DEFAULT 0 COMMENT '攻击前飞行距离',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '武器描述',
  `weapon_type_id_3` int NULL DEFAULT NULL COMMENT '武器类型id第3级（扩展）',
  `weapon_type_id_4` int NULL DEFAULT NULL COMMENT '武器类型id第4级（扩展）',
  `weapon_type_id_5` int NULL DEFAULT NULL COMMENT '武器类型id第5级（扩展）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_weapon_model_id`(`weapon_model_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '武器型号档案' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `weapon_type`;
CREATE TABLE `weapon_type`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `weapon_type_id` int NOT NULL COMMENT '武器类型id（业务编号）',
  `weapon_type_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '武器类型名称（如：主炮、副炮、鱼雷等）',
  `level` int NOT NULL DEFAULT 1 COMMENT '武器类型等级（层级，用于分类结构，例如1级=主类，2级=子类）',
  `parent_id` int NULL DEFAULT NULL COMMENT '父级id（指向上级武器类型id，顶级类型为空）',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '武器描述（文字说明，如武器特性、用途等）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_weapon_type_id`(`weapon_type_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '武器类型档案' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
