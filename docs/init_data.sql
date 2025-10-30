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
