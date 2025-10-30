-- 标签应用运营监控相关表结构与测试数据
CREATE TABLE IF NOT EXISTS tag_usage_summary (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tag_name VARCHAR(128) NOT NULL,
    application_count INT NOT NULL,
    period_start DATE NOT NULL,
    period_end DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS tag_usage_frequency_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tag_name VARCHAR(128) NOT NULL,
    frequency_type VARCHAR(32) NOT NULL,
    average_frequency DECIMAL(10,2) NOT NULL,
    reference_period VARCHAR(32) NOT NULL,
    calculated_at DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS tag_impact_range_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tag_name VARCHAR(128) NOT NULL,
    impacted_users INT NOT NULL,
    impacted_segments VARCHAR(256) NOT NULL,
    scope_dimension VARCHAR(64) NOT NULL,
    updated_at DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS tag_trigger_log (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tag_name VARCHAR(128) NOT NULL,
    trigger_count INT NOT NULL,
    success_rate DECIMAL(10,4) NOT NULL,
    monitoring_window VARCHAR(64) NOT NULL,
    monitored_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS tag_effectiveness_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tag_name VARCHAR(128) NOT NULL,
    conversion_rate DECIMAL(10,4) NOT NULL,
    engagement_score DECIMAL(10,2) NOT NULL,
    evaluation_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS tag_feedback_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tag_name VARCHAR(128) NOT NULL,
    feedback_score DECIMAL(10,2) NOT NULL,
    positive_feedback INT NOT NULL,
    negative_feedback INT NOT NULL,
    feedback_period VARCHAR(32) NOT NULL,
    collected_at DATE NOT NULL
);

INSERT INTO tag_usage_summary(tag_name, application_count, period_start, period_end)
VALUES
    ('高价值用户', 156, '2023-07-01', '2023-07-31'),
    ('高价值用户', 143, '2023-06-01', '2023-06-30');

INSERT INTO tag_usage_frequency_record(tag_name, frequency_type, average_frequency, reference_period, calculated_at)
VALUES
    ('高价值用户', 'DAILY', 5.20, '2023-07', '2023-07-31 20:10:00'),
    ('高价值用户', 'WEEKLY', 32.10, '2023-W30', '2023-07-28 18:30:00');

INSERT INTO tag_impact_range_record(tag_name, impacted_users, impacted_segments, scope_dimension, updated_at)
VALUES
    ('高价值用户', 8650, '华东地区|高净值', '地区', '2023-07-31 09:35:00'),
    ('高价值用户', 5420, '华南地区|高净值', '地区', '2023-07-24 11:50:00');

INSERT INTO tag_trigger_log(tag_name, trigger_count, success_rate, monitoring_window, monitored_date)
VALUES
    ('高价值用户', 45, 0.9100, '2023-07-31', '2023-07-31'),
    ('高价值用户', 52, 0.8750, '2023-07-30', '2023-07-30');

INSERT INTO tag_effectiveness_record(tag_name, conversion_rate, engagement_score, evaluation_date)
VALUES
    ('高价值用户', 0.2300, 88.50, '2023-07-31'),
    ('高价值用户', 0.2150, 85.20, '2023-07-24');

INSERT INTO tag_feedback_record(tag_name, feedback_score, positive_feedback, negative_feedback, feedback_period, collected_at)
VALUES
    ('高价值用户', 4.60, 56, 4, '2023-07', '2023-07-30'),
    ('高价值用户', 4.40, 43, 6, '2023-06', '2023-06-29');

-- 敏感数据脱敏加密规则配置维护相关表结构与测试数据
CREATE TABLE IF NOT EXISTS masking_rule_config (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    data_type VARCHAR(128) NOT NULL,
    field_name VARCHAR(128) NOT NULL,
    masking_strategy VARCHAR(128) NOT NULL,
    encryption_algorithm VARCHAR(128),
    policy_reference VARCHAR(256),
    created_at DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS encryption_key (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    key_name VARCHAR(128) NOT NULL,
    algorithm VARCHAR(64) NOT NULL,
    key_length INT NOT NULL,
    generated_at DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS key_storage_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    key_id BIGINT NOT NULL,
    storage_location VARCHAR(128) NOT NULL,
    protection_level VARCHAR(64) NOT NULL,
    stored_at DATETIME NOT NULL,
    CONSTRAINT fk_key_storage_key FOREIGN KEY (key_id) REFERENCES encryption_key(id)
);

CREATE TABLE IF NOT EXISTS key_distribution_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    key_id BIGINT NOT NULL,
    target_system VARCHAR(128) NOT NULL,
    distribution_method VARCHAR(64) NOT NULL,
    distributed_at DATETIME NOT NULL,
    CONSTRAINT fk_key_distribution_key FOREIGN KEY (key_id) REFERENCES encryption_key(id)
);

CREATE TABLE IF NOT EXISTS key_rotation_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    key_id BIGINT NOT NULL,
    rotation_period_days INT NOT NULL,
    next_rotation_date DATE,
    updated_at DATETIME NOT NULL,
    CONSTRAINT fk_key_rotation_key FOREIGN KEY (key_id) REFERENCES encryption_key(id)
);

INSERT INTO masking_rule_config(data_type, field_name, masking_strategy, encryption_algorithm, policy_reference, created_at)
VALUES
    ('身份证号', 'id_card', 'MASK_MIDDLE', 'SM4', '安全制度-第12条', NOW()),
    ('手机号', 'mobile', 'MASK_KEEP_LAST4', 'AES', '安全制度-第13条', NOW());

INSERT INTO encryption_key(key_name, algorithm, key_length, generated_at)
VALUES
    ('order-data-key', 'AES', 256, NOW()),
    ('profile-data-key', 'SM4', 128, NOW());

INSERT INTO key_storage_record(key_id, storage_location, protection_level, stored_at)
VALUES
    (1, 'HSM-Cluster-A', 'LEVEL-1', NOW()),
    (2, 'Vault-Cluster-B', 'LEVEL-2', NOW());

INSERT INTO key_distribution_record(key_id, target_system, distribution_method, distributed_at)
VALUES
    (1, 'marketing-engine', 'API_PUSH', NOW()),
    (2, 'data-warehouse', 'MANUAL', NOW());

INSERT INTO key_rotation_record(key_id, rotation_period_days, next_rotation_date, updated_at)
VALUES
    (1, 90, '2023-10-01', NOW()),
    (1, 90, '2023-07-01', DATE_SUB(NOW(), INTERVAL 90 DAY));

-- 数据校验规则库相关表结构与测试数据
CREATE TABLE IF NOT EXISTS validation_rule_entry (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    rule_name VARCHAR(256) NOT NULL,
    rule_category VARCHAR(128) NOT NULL,
    rule_expression VARCHAR(512) NOT NULL,
    description VARCHAR(512),
    created_at DATETIME NOT NULL
);

INSERT INTO validation_rule_entry(rule_name, rule_category, rule_expression, description, created_at)
VALUES
    ('营销订单金额不能为空', '完整性', 'amount IS NOT NULL', '保障订单金额字段必须有值', NOW()),
    ('客户手机号格式校验', '格式', 'mobile REGEXP "^1[3-9]\\d{9}$"', '校验手机号符合国内规则', NOW()),
    ('账期不得晚于当前日期', '及时性', 'billing_date <= CURRENT_DATE()', '确保账期不可超前', NOW());
