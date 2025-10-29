DROP TABLE IF EXISTS data_asset;
CREATE TABLE data_asset (
    id BIGINT PRIMARY KEY,
    name VARCHAR(128),
    domain VARCHAR(128),
    owner VARCHAR(128),
    description VARCHAR(255),
    storage_type VARCHAR(64),
    backup_strategy VARCHAR(128),
    archive_policy VARCHAR(128),
    deletion_policy VARCHAR(128),
    updated_at TIMESTAMP
);
INSERT INTO data_asset VALUES
(1,'客户资产','营销域','张三','客户核心数据','HDFS','DAILY retention:30','Archive after 60 days to 对象存储','逻辑删除 with approval','2024-05-01 10:00:00');

DROP TABLE IF EXISTS metadata_record;
CREATE TABLE metadata_record (
    id BIGINT PRIMARY KEY,
    data_source VARCHAR(128),
    structure VARCHAR(255),
    attributes VARCHAR(255),
    relations VARCHAR(255)
);
INSERT INTO metadata_record VALUES
(1,'ODS_MARKETING','表：customer_usage','customer_id, usage, fee','关联 customer');

DROP TABLE IF EXISTS validation_record;
CREATE TABLE validation_record (
    id BIGINT PRIMARY KEY,
    category VARCHAR(64),
    rule_name VARCHAR(128),
    status VARCHAR(32),
    detail VARCHAR(255)
);
INSERT INTO validation_record VALUES
(1,'BUSINESS_RULE','状态校验','PASS','业务规则验证通过 -> 订单清洗');

DROP TABLE IF EXISTS energy_usage_forecast;
CREATE TABLE energy_usage_forecast (
    id BIGINT PRIMARY KEY,
    customer_no VARCHAR(64),
    forecast_date DATE,
    predicted_consumption DOUBLE,
    predicted_cost DOUBLE,
    high_risk TINYINT
);
INSERT INTO energy_usage_forecast VALUES
(1,'C001','2024-06-01',1260,920,1);

DROP TABLE IF EXISTS redundant_data_record;
CREATE TABLE redundant_data_record (
    id BIGINT PRIMARY KEY,
    table_name VARCHAR(128),
    hash_value VARCHAR(128),
    description VARCHAR(255)
);
INSERT INTO redundant_data_record VALUES
(1,'dwd_customer','abc123','重复字段');

DROP TABLE IF EXISTS data_lifecycle_record;
CREATE TABLE data_lifecycle_record (
    id BIGINT PRIMARY KEY,
    dataset_name VARCHAR(128),
    lifecycle_stage VARCHAR(64),
    start_date DATE,
    end_date DATE
);
INSERT INTO data_lifecycle_record VALUES
(1,'dws_sales','LIFECYCLE_RECORD','2024-05-01',NULL);

DROP TABLE IF EXISTS slow_sql_record;
CREATE TABLE slow_sql_record (
    id BIGINT PRIMARY KEY,
    category VARCHAR(64),
    statement TEXT,
    optimization_strategy VARCHAR(255)
);
INSERT INTO slow_sql_record VALUES
(1,'QUERY_CATEGORY','select * from big_table','分类管理查询语句: 添加索引');

DROP TABLE IF EXISTS data_authority_record;
CREATE TABLE data_authority_record (
    id BIGINT PRIMARY KEY,
    dataset_name VARCHAR(128),
    authority_name VARCHAR(128),
    description VARCHAR(255),
    contact VARCHAR(128)
);
INSERT INTO data_authority_record VALUES
(1,'营销主题','主数据组','权威数据源','leader@example.com');

DROP TABLE IF EXISTS permission_record;
CREATE TABLE permission_record (
    id BIGINT PRIMARY KEY,
    role VARCHAR(64),
    resource VARCHAR(128),
    permission VARCHAR(64)
);
INSERT INTO permission_record VALUES
(1,'ADMIN','资产清单','FULL_CONTROL');

DROP TABLE IF EXISTS security_rule;
CREATE TABLE security_rule (
    id BIGINT PRIMARY KEY,
    rule_type VARCHAR(64),
    target VARCHAR(128),
    strategy VARCHAR(128)
);
INSERT INTO security_rule VALUES
(1,'ENCRYPTION','customer_phone','AES256');

DROP TABLE IF EXISTS metric_record;
CREATE TABLE metric_record (
    id BIGINT PRIMARY KEY,
    metric_code VARCHAR(64),
    dimension VARCHAR(64),
    reference_date DATE,
    value DOUBLE,
    remark VARCHAR(255)
);
INSERT INTO metric_record VALUES
(1,'GMV','HISTORICAL','2024-05-01',12345,'Recorded HISTORICAL');

DROP TABLE IF EXISTS tag_usage_record;
CREATE TABLE tag_usage_record (
    id BIGINT PRIMARY KEY,
    tag_code VARCHAR(64),
    metric VARCHAR(64),
    value DOUBLE
);
INSERT INTO tag_usage_record VALUES
(1,'TAG_A','COUNT',120);

DROP TABLE IF EXISTS key_management_record;
CREATE TABLE key_management_record (
    id BIGINT PRIMARY KEY,
    action VARCHAR(64),
    key_name VARCHAR(128),
    algorithm VARCHAR(64),
    action_time TIMESTAMP
);
INSERT INTO key_management_record VALUES
(1,'KEY_GENERATE','enc_key','AES256','2024-05-01 09:00:00');

DROP TABLE IF EXISTS quality_rule;
CREATE TABLE quality_rule (
    id BIGINT PRIMARY KEY,
    rule_name VARCHAR(128),
    expression VARCHAR(255),
    description VARCHAR(255)
);
INSERT INTO quality_rule VALUES
(1,'非空校验','column is not null','确保字段非空');
