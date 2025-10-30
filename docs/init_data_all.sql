DROP TABLE IF EXISTS asset_deletion_request;
DROP TABLE IF EXISTS asset_archive_record;
DROP TABLE IF EXISTS asset_backup_plan;
DROP TABLE IF EXISTS asset_storage_change;
DROP TABLE IF EXISTS marketing_data_asset;
DROP TABLE IF EXISTS metadata_item;
DROP TABLE IF EXISTS business_rule;
DROP TABLE IF EXISTS data_rule;
DROP TABLE IF EXISTS development_standard_rule;
DROP TABLE IF EXISTS naming_standard_rule;
DROP TABLE IF EXISTS naming_adaptation_rule;
DROP TABLE IF EXISTS electricity_usage_record;
DROP TABLE IF EXISTS high_price_warning_config;

CREATE TABLE marketing_data_asset (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    asset_name VARCHAR(128) NOT NULL,
    asset_type VARCHAR(64) NOT NULL,
    owner VARCHAR(64) NOT NULL,
    importance_level VARCHAR(32) NOT NULL,
    status VARCHAR(32) NOT NULL,
    storage_level VARCHAR(32) NOT NULL,
    created_at DATETIME NOT NULL
);

CREATE TABLE asset_storage_change (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    asset_id BIGINT NOT NULL,
    storage_level VARCHAR(32) NOT NULL,
    operator VARCHAR(64) NOT NULL,
    change_time DATETIME NOT NULL,
    FOREIGN KEY (asset_id) REFERENCES marketing_data_asset(id)
);

CREATE TABLE asset_backup_plan (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    asset_id BIGINT NOT NULL,
    backup_strategy VARCHAR(128) NOT NULL,
    schedule_time DATETIME,
    operator VARCHAR(64) NOT NULL,
    status VARCHAR(32) NOT NULL,
    FOREIGN KEY (asset_id) REFERENCES marketing_data_asset(id)
);

CREATE TABLE asset_archive_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    asset_id BIGINT NOT NULL,
    archive_reason VARCHAR(256) NOT NULL,
    archive_date DATETIME NOT NULL,
    operator VARCHAR(64) NOT NULL,
    FOREIGN KEY (asset_id) REFERENCES marketing_data_asset(id)
);

CREATE TABLE asset_deletion_request (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    asset_id BIGINT NOT NULL,
    reason VARCHAR(256) NOT NULL,
    approval_user VARCHAR(64) NOT NULL,
    status VARCHAR(32) NOT NULL,
    request_time DATETIME NOT NULL,
    FOREIGN KEY (asset_id) REFERENCES marketing_data_asset(id)
);

CREATE TABLE metadata_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    metadata_code VARCHAR(64) NOT NULL,
    metadata_name VARCHAR(128) NOT NULL,
    data_source VARCHAR(64) NOT NULL,
    data_structure VARCHAR(256) NOT NULL,
    attributes VARCHAR(256),
    relationships VARCHAR(256),
    description VARCHAR(256)
);

CREATE TABLE business_rule (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    rule_code VARCHAR(64) NOT NULL,
    rule_name VARCHAR(128) NOT NULL,
    rule_type VARCHAR(32) NOT NULL,
    threshold_value DOUBLE,
    description VARCHAR(256)
);

CREATE TABLE data_rule (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    rule_code VARCHAR(64) NOT NULL,
    field_name VARCHAR(64) NOT NULL,
    regex_pattern VARCHAR(256) NOT NULL,
    description VARCHAR(256)
);

CREATE TABLE development_standard_rule (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    rule_type VARCHAR(64) NOT NULL,
    rule_code VARCHAR(64) NOT NULL,
    rule_name VARCHAR(128) NOT NULL,
    requirement VARCHAR(256),
    validator_regex VARCHAR(256)
);

CREATE TABLE naming_standard_rule (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    rule_type VARCHAR(64) NOT NULL,
    rule_code VARCHAR(64) NOT NULL,
    rule_name VARCHAR(128) NOT NULL,
    pattern VARCHAR(256) NOT NULL,
    description VARCHAR(256)
);

CREATE TABLE naming_adaptation_rule (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    rule_code VARCHAR(64) NOT NULL,
    rule_name VARCHAR(128) NOT NULL,
    prefix VARCHAR(64),
    suffix VARCHAR(64),
    description VARCHAR(256)
);

CREATE TABLE electricity_usage_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    month_tag VARCHAR(16) NOT NULL,
    usage_kwh DOUBLE NOT NULL,
    cost_amount DOUBLE NOT NULL,
    record_time DATETIME NOT NULL
);

CREATE TABLE high_price_warning_config (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cost_threshold DOUBLE NOT NULL,
    usage_threshold DOUBLE NOT NULL,
    strategy_description VARCHAR(256)
);

INSERT INTO marketing_data_asset(asset_name, asset_type, owner, importance_level, status, storage_level, created_at) VALUES
('渠道交易明细', '交易', 'marketing_ops', '高', 'ACTIVE', 'HOT', '2024-01-05 10:00:00'),
('客户画像主表', '客户', 'data_lab', '高', 'ACTIVE', 'WARM', '2024-02-12 09:30:00'),
('公共服务访问日志', '日志', 'platform_team', '中', 'ACTIVE', 'COLD', '2024-03-18 11:20:00');

INSERT INTO metadata_item(metadata_code, metadata_name, data_source, data_structure, attributes, relationships, description) VALUES
('MD001', '客户主数据', 'CRM', '{"fields":10}', '姓名,联系方式,地址', '客户-订单', 'CRM 核心客户数据'),
('MD002', '渠道订单', 'OMS', '{"fields":15}', '订单号,渠道,金额', '订单-支付', '线上渠道订单数据'),
('MD003', '营销策略', 'DMP', '{"fields":8}', '策略编号,覆盖人群', '策略-活动', '营销活动策略配置');

INSERT INTO business_rule(rule_code, rule_name, rule_type, threshold_value, description) VALUES
('BR_MAX_COST', '营销活动成本上限', 'THRESHOLD', 50000, '单次活动成本不得超过 50000'),
('BR_MIN_USAGE', '日均电量使用下限', 'MINIMUM', 120, '重点用户日均电量需保持在 120 以上');

INSERT INTO data_rule(rule_code, field_name, regex_pattern, description) VALUES
('DR_PHONE_FORMAT', 'mobile', '1[3-9]\\d{9}', '手机号必须为国内 11 位数字'),
('DR_ORDER_CODE', 'orderCode', '[A-Z]{3}-\\d{6}', '订单编码格式必须满足 AAA-000000');

INSERT INTO development_standard_rule(rule_type, rule_code, rule_name, requirement, validator_regex) VALUES
('TABLE_SCHEMA', 'TAB_STD_001', 'ODS 层建表规范', 'CREATE TABLE 需包含业务日期字段', '.*biz_date.*'),
('FIELD_TYPE', 'FLD_STD_001', '字段类型规范', NULL, '^(STRING|INT|BIGINT|DECIMAL\\(\\d+,\\d+\\))$'),
('LINEAGE', 'LNG_STD_001', '血缘规范', NULL, '.*->.*'),
('CODING', 'COD_STD_001', '编码规范', NULL, '.*SELECT.*FROM.*'),
('LAYERING', 'LYR_STD_001', '分层规范', 'DWS', NULL),
('SCHEDULING', 'SCH_STD_001', '调度规范', NULL, '^([0-5]?\\d\s){4}[0-5]?\\d$'),
('INTEGRATION', 'INT_STD_001', '集成接口命名规范', 'INTF_', NULL);

INSERT INTO naming_standard_rule(rule_type, rule_code, rule_name, pattern, description) VALUES
('TABLE', 'TAB_NAM_001', '表模型命名规范', '^md_\\w+_(dw|dm)$', '表名必须以 md_ 开头，以 dw/dm 结尾'),
('NODE', 'NODE_NAM_001', '节点命名规范', '^[a-z]+_[a-z]+_node$', '节点名称由两个英文单词加 _node 组成'),
('SPACE', 'SPACE_NAM_001', '项目空间命名规范', '^proj_[a-z]{3,}$', '项目空间以 proj_ 开头，后接至少三个字母');

INSERT INTO naming_adaptation_rule(rule_code, rule_name, prefix, suffix, description) VALUES
('ADAPT_RULE_001', '营销域命名适配', 'md_', '_std', '为营销域对象增加统一前后缀');

INSERT INTO electricity_usage_record(user_id, month_tag, usage_kwh, cost_amount, record_time) VALUES
(1001, '2024-01', 320.5, 420.80, '2024-01-31 12:00:00'),
(1001, '2024-02', 335.0, 438.10, '2024-02-29 12:00:00'),
(1001, '2024-03', 350.2, 465.60, '2024-03-31 12:00:00'),
(1002, '2024-01', 210.0, 285.30, '2024-01-31 12:00:00'),
(1002, '2024-02', 198.4, 270.10, '2024-02-29 12:00:00'),
(1002, '2024-03', 205.6, 278.45, '2024-03-31 12:00:00');

INSERT INTO high_price_warning_config(cost_threshold, usage_threshold, strategy_description) VALUES
(450.00, 320.00, '成本超过 450 或月用电量超过 320 触发预警');


-- 冗余数据识别相关表
DROP TABLE IF EXISTS redundant_data_record;
CREATE TABLE redundant_data_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    domain_name VARCHAR(100) NOT NULL,
    table_name VARCHAR(128) NOT NULL,
    hash_signature VARCHAR(64) NOT NULL,
    issue_status VARCHAR(32) NOT NULL,
    master_dataset VARCHAR(128) NOT NULL,
    created_at DATETIME NOT NULL
);
INSERT INTO redundant_data_record(domain_name, table_name, hash_signature, issue_status, master_dataset, created_at) VALUES
('营销活动', 'campaign_raw', 'hash001', 'OPEN', 'campaign_master', NOW()),
('营销活动', 'campaign_backup', 'hash002', 'RESOLVED', 'campaign_master', NOW()),
('客户域', 'customer_snapshot', 'hash003', 'OPEN', 'customer_master', NOW());

-- 数据存储周期记录
DROP TABLE IF EXISTS data_lifecycle_record;
CREATE TABLE data_lifecycle_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    dataset_name VARCHAR(128) NOT NULL,
    business_process VARCHAR(128) NOT NULL,
    retention_days INT NOT NULL,
    owner VARCHAR(64) NOT NULL,
    created_at DATETIME NOT NULL
);
INSERT INTO data_lifecycle_record(dataset_name, business_process, retention_days, owner, created_at) VALUES
('customer_order', '订单履约', 60, 'li.si', NOW());

DROP TABLE IF EXISTS storage_cycle_plan;
CREATE TABLE storage_cycle_plan (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    dataset_name VARCHAR(128) NOT NULL,
    strategy_type VARCHAR(32) NOT NULL,
    recommended_days INT NOT NULL,
    notes VARCHAR(255),
    requested_by VARCHAR(64),
    created_at DATETIME NOT NULL
);
INSERT INTO storage_cycle_plan(dataset_name, strategy_type, recommended_days, notes, requested_by, created_at) VALUES
('real_time_event', 'HIGH_SENSITIVITY', 14, '实时事件仅需保留两周', 'wang.wu', NOW()),
('customer_profile', 'LONG_TERM', 730, '每年归档一次并同步冷存储', 'admin', NOW());

-- 慢SQL优化记录
DROP TABLE IF EXISTS slow_query_optimization;
CREATE TABLE slow_query_optimization (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(64) NOT NULL,
    sample_sql TEXT NOT NULL,
    optimization_suggestion VARCHAR(255) NOT NULL,
    expected_gain INT NOT NULL,
    created_at DATETIME NOT NULL
);
INSERT INTO slow_query_optimization(category_name, sample_sql, optimization_suggestion, expected_gain, created_at) VALUES
('缺索引查询', 'SELECT * FROM orders WHERE customer_id = ?', '增加customer_id索引', 60, NOW());

DROP TABLE IF EXISTS database_structure_strategy;
CREATE TABLE database_structure_strategy (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    strategy_name VARCHAR(128) NOT NULL,
    adjustment_detail VARCHAR(255) NOT NULL,
    expected_impact VARCHAR(255) NOT NULL,
    proposer VARCHAR(64) NOT NULL,
    created_at DATETIME NOT NULL
);
INSERT INTO database_structure_strategy(strategy_name, adjustment_detail, expected_impact, proposer, created_at) VALUES
('订单表分区', '按照月份新增HASH分区，提升写入效率', '减少写入锁等待', 'ops', NOW());

DROP TABLE IF EXISTS database_monitoring_record;
CREATE TABLE database_monitoring_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    monitor_date DATE NOT NULL,
    slow_query_count INT NOT NULL,
    max_response_time INT NOT NULL,
    notes VARCHAR(255),
    created_at DATETIME NOT NULL
);
INSERT INTO database_monitoring_record(monitor_date, slow_query_count, max_response_time, notes, created_at) VALUES
('2024-05-01', 23, 4500, '凌晨批处理波动', NOW());

-- 数据定源管理
DROP TABLE IF EXISTS authority_source_entry;
CREATE TABLE authority_source_entry (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    domain_name VARCHAR(100) NOT NULL,
    source_system VARCHAR(128) NOT NULL,
    description VARCHAR(255) NOT NULL,
    owner VARCHAR(64) NOT NULL,
    last_verified_at DATETIME NOT NULL
);
INSERT INTO authority_source_entry(domain_name, source_system, description, owner, last_verified_at) VALUES
('营销域', 'crm_system', 'CRM同步的客户主数据', 'marketing_team', NOW()),
('营销域', 'ad_platform', '广告平台曝光数据', 'marketing_team', NOW());

DROP TABLE IF EXISTS indicator_lineage_record;
CREATE TABLE indicator_lineage_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    indicator_code VARCHAR(64) NOT NULL,
    indicator_name VARCHAR(128) NOT NULL,
    source_table VARCHAR(128) NOT NULL,
    source_field VARCHAR(128) NOT NULL,
    lineage_path VARCHAR(255) NOT NULL,
    last_updated_at DATETIME NOT NULL
);
INSERT INTO indicator_lineage_record(indicator_code, indicator_name, source_table, source_field, lineage_path, last_updated_at) VALUES
('MKT_CONV_RATE', '转化率', 'fact_campaign', 'conversion_rate', 'ods.fact_campaign -> dwd.campaign_metrics -> ads.marketing_dashboard', NOW());

-- 数据责任人管理
DROP TABLE IF EXISTS admin_permission;
CREATE TABLE admin_permission (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    admin_user_id BIGINT NOT NULL,
    permission_type VARCHAR(64) NOT NULL,
    granted_by VARCHAR(64) NOT NULL,
    granted_at DATETIME NOT NULL,
    expire_at DATETIME NULL
);
INSERT INTO admin_permission(admin_user_id, permission_type, granted_by, granted_at, expire_at) VALUES
(1001, 'ASSET_MAINTAIN', 'system', NOW(), '2025-12-31 23:59:59');

DROP TABLE IF EXISTS user_lineage_access;
CREATE TABLE user_lineage_access (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    business_relation VARCHAR(128) NOT NULL,
    access_scope VARCHAR(64) NOT NULL,
    approver VARCHAR(64) NOT NULL,
    authorized_at DATETIME NOT NULL
);
INSERT INTO user_lineage_access(user_id, business_relation, access_scope, approver, authorized_at) VALUES
(2008, '渠道运营', 'READ_ONLY', 'admin', NOW());

-- 数据安全策略
DROP TABLE IF EXISTS data_encryption_policy;
CREATE TABLE data_encryption_policy (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    dataset_name VARCHAR(128) NOT NULL UNIQUE,
    algorithm VARCHAR(64) NOT NULL,
    key_identifier VARCHAR(128) NOT NULL,
    enabled TINYINT NOT NULL,
    updated_at DATETIME NOT NULL
);
INSERT INTO data_encryption_policy(dataset_name, algorithm, key_identifier, enabled, updated_at) VALUES
('customer_profile', 'AES256', 'kms-key-01', 1, NOW());

DROP TABLE IF EXISTS access_control_policy;
CREATE TABLE access_control_policy (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(128) NOT NULL,
    dataset_name VARCHAR(128) NOT NULL,
    access_level VARCHAR(32) NOT NULL,
    reviewer VARCHAR(64) NOT NULL,
    updated_at DATETIME NOT NULL,
    UNIQUE KEY uk_role_dataset(role_name, dataset_name)
);
INSERT INTO access_control_policy(role_name, dataset_name, access_level, reviewer, updated_at) VALUES
('marketing-analyst', 'campaign_fact', 'READ', 'security_lead', NOW());

DROP TABLE IF EXISTS data_masking_rule;
CREATE TABLE data_masking_rule (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    dataset_name VARCHAR(128) NOT NULL,
    field_name VARCHAR(128) NOT NULL,
    masking_rule VARCHAR(128) NOT NULL,
    sample VARCHAR(128),
    updated_at DATETIME NOT NULL,
    UNIQUE KEY uk_dataset_field(dataset_name, field_name)
);
INSERT INTO data_masking_rule(dataset_name, field_name, masking_rule, sample, updated_at) VALUES
('customer_profile', 'mobile', 'MASK_MIDDLE', '138****5678', NOW());

-- 营销指标监控
DROP TABLE IF EXISTS indicator_snapshot;
CREATE TABLE indicator_snapshot (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    indicator_code VARCHAR(64) NOT NULL,
    indicator_name VARCHAR(128) NOT NULL,
    snapshot_date DATE NOT NULL,
    snapshot_value DOUBLE NOT NULL,
    created_at DATETIME NOT NULL
);
INSERT INTO indicator_snapshot(indicator_code, indicator_name, snapshot_date, snapshot_value, created_at) VALUES
('MKT_CONV_RATE', '转化率', '2023-05-02', 1.05, NOW()),
('MKT_CONV_RATE', '转化率', '2023-05-03', 1.08, NOW()),
('MKT_CONV_RATE', '转化率', '2024-05-01', 1.2, NOW()),
('MKT_CONV_RATE', '转化率', '2024-05-02', 1.3, NOW()),
('MKT_CONV_RATE', '转化率', '2024-05-03', 1.4, NOW());

DROP TABLE IF EXISTS indicator_trend_record;
CREATE TABLE indicator_trend_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    indicator_code VARCHAR(64) NOT NULL,
    period_type VARCHAR(16) NOT NULL,
    period_start DATE NOT NULL,
    period_end DATE NOT NULL,
    average_value DOUBLE,
    max_value DOUBLE,
    min_value DOUBLE,
    created_at DATETIME NOT NULL
);
INSERT INTO indicator_trend_record(indicator_code, period_type, period_start, period_end, average_value, max_value, min_value, created_at) VALUES
('MKT_CONV_RATE', 'DAY', '2024-05-01', '2024-05-01', 1.2, 1.2, 1.2, NOW()),
('MKT_CONV_RATE', 'WEEK', '2024-05-01', '2024-05-07', 1.25, 1.3, 1.2, NOW()),
('MKT_CONV_RATE', 'MONTH', '2024-05-01', '2024-05-31', 1.28, 1.4, 1.1, NOW());

DROP TABLE IF EXISTS indicator_comparison_record;
CREATE TABLE indicator_comparison_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    indicator_code VARCHAR(64) NOT NULL,
    compare_type VARCHAR(16) NOT NULL,
    current_period VARCHAR(32) NOT NULL,
    previous_period VARCHAR(32) NOT NULL,
    current_value DOUBLE NOT NULL,
    previous_value DOUBLE NOT NULL,
    difference_rate DOUBLE NOT NULL,
    created_at DATETIME NOT NULL
);
INSERT INTO indicator_comparison_record(indicator_code, compare_type, current_period, previous_period, current_value, previous_value, difference_rate, created_at) VALUES
('MKT_CONV_RATE', 'YOY', '2024-05-02', '2023-05-02', 1.30, 1.05, 0.238, NOW()),
('MKT_CONV_RATE', 'YOY', '2024-05-03', '2023-05-03', 1.40, 1.08, 0.296, NOW()),
('MKT_CONV_RATE', 'MOM', '2024-05-02', '2024-05-01', 1.30, 1.20, 0.083, NOW()),
('MKT_CONV_RATE', 'MOM', '2024-05-03', '2024-05-02', 1.40, 1.30, 0.077, NOW());

DROP TABLE IF EXISTS indicator_realtime_summary;
CREATE TABLE indicator_realtime_summary (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    indicator_category VARCHAR(64) NOT NULL,
    total_indicators INT NOT NULL,
    active_alerts INT NOT NULL,
    responsible_owner VARCHAR(64) NOT NULL,
    system_calls INT NOT NULL,
    access_count INT NOT NULL,
    average_response_ms INT NOT NULL,
    last_updated_at DATETIME NOT NULL
);
INSERT INTO indicator_realtime_summary(indicator_category, total_indicators, active_alerts, responsible_owner, system_calls, access_count, average_response_ms, last_updated_at) VALUES
('关键业务指标', 12, 0, 'marketing_ops', 350, 1200, 180, NOW()),
('关键业务指标', 12, 2, 'marketing_ops', 360, 1350, 220, NOW());


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


