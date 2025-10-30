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
('MKT_CONV_RATE', '转化率', '2024-05-01', 1.2, NOW()),
('MKT_CONV_RATE', '转化率', '2024-05-02', 1.3, NOW());

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
('MKT_CONV_RATE', 'YOY', '2024-05', '2023-05', 1.35, 1.10, 0.227, NOW()),
('MKT_CONV_RATE', 'MOM', '2024-05', '2024-04', 1.35, 1.20, 0.125, NOW());

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
