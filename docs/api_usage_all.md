## 1. 导出相关冗余数据清单 `/api/data-redundancy/export`
- **入参**：`domainName`(必填, 要导出的业务领域)、`outputFormat`(必填, 仅支持 CSV 或 JSON)、`includeResolved`(可选, 是否包含已整改记录)
- **出参**：`success`(布尔标记请求是否成功)、`total`(满足条件的冗余条目数量)、`exportFormat`(导出格式)、`records`(冗余记录列表)
- **用法描述**：根据领域名称导出冗余数据记录，可选择导出格式和是否包含已整改项。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-redundancy/export -H "Content-Type: application/json" -d '{"domainName":"营销活动","outputFormat":"CSV","includeResolved":true}'
    - **预期返回**：`success=true`，`total=2`，`exportFormat="CSV"`，`records`包含`campaign_raw`与`campaign_backup`两条冗余记录。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-redundancy/export -H "Content-Type: application/json" -d '{"domainName":"营销活动","outputFormat":"JSON","includeResolved":false}'
    - **预期返回**：`success=true`，`total=1`，`exportFormat="JSON"`，`records`仅返回状态为`OPEN`的`campaign_raw`记录。
  - ❌ 示例：curl -X POST http://localhost:8080/api/data-redundancy/export -H "Content-Type: application/json" -d '{"domainName":"营销活动","outputFormat":"TXT"}'
    - **预期返回**：HTTP 500，`{"code":500,"message":"导出格式只支持JSON或CSV"}`。

## 2. 记录数据生命周期 `/api/data-storage-cycle/lifecycle`
- **入参**：`datasetName`(必填, 数据集名称)、`businessProcess`(可选, 所属业务流程)、`retentionDays`(必填, 保留天数>0)、`owner`(必填, 负责人账号)
- **出参**：`success`(布尔状态)、`records`(该数据集的生命周期记录列表)
- **用法描述**：记录业务数据集的生命周期设定并返回当前记录列表。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-storage-cycle/lifecycle -H "Content-Type: application/json" -d '{"datasetName":"customer_order","businessProcess":"订单履约","retentionDays":60,"owner":"li.si"}'
    - **预期返回**：`success=true`，`records`包含新增的 60 天生命周期记录，并保留历史的`customer_order`记录。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-storage-cycle/lifecycle -H "Content-Type: application/json" -d '{"datasetName":"customer_order","businessProcess":"售后跟进","retentionDays":45,"owner":"liu.qi"}'
    - **预期返回**：`success=true`，`records`列表追加一条`retentionDays=45`的记录，可见不同业务流程的配置。
  - ❌ 示例：curl -X POST http://localhost:8080/api/data-storage-cycle/lifecycle -H "Content-Type: application/json" -d '{"datasetName":"customer_order","retentionDays":0,"owner":""}'
    - **预期返回**：HTTP 500，错误信息提示保留天数需大于 0 且负责人不能为空。

## 3. 数据存储周期规划-时效性较强的数据，适当缩短存储周期 `/api/data-storage-cycle/plan/high-sensitivity`
- **入参**：`datasetName`(必填, 数据集名称)、`recommendedDays`(必填, 1-90 天的建议期限)、`justification`(必填, 缩短理由不少于5个字符)、`requestedBy`(必填, 申请人)
- **出参**：`success`(布尔状态)、`plans`(高敏数据的缩短策略列表)
- **用法描述**：为高时效数据制定短周期存储计划。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-storage-cycle/plan/high-sensitivity -H "Content-Type: application/json" -d '{"datasetName":"real_time_event","recommendedDays":14,"justification":"实时事件仅需保留两周","requestedBy":"wang.wu"}'
    - **预期返回**：`success=true`，`plans`列表含新计划，显示`recommendedDays=14`、`strategyType=HIGH_SENSITIVITY`。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-storage-cycle/plan/high-sensitivity -H "Content-Type: application/json" -d '{"datasetName":"real_time_event","recommendedDays":30,"justification":"运营需要保留一个月","requestedBy":"ops_user"}'
    - **预期返回**：`success=true`，`plans`列表包含最近两次申请记录，最新记录`recommendedDays=30`排在最前。
  - ❌ 示例：curl -X POST http://localhost:8080/api/data-storage-cycle/plan/high-sensitivity -H "Content-Type: application/json" -d '{"datasetName":"real_time_event","recommendedDays":120,"justification":"短期","requestedBy":""}'
    - **预期返回**：HTTP 500，错误信息提示推荐天数需在 1-90 内且理由/申请人不可为空。

## 4. 数据存储周期规划-长期业务数据，考虑长期存储策略 `/api/data-storage-cycle/plan/long-term`
- **入参**：`datasetName`(必填, 数据集名称)、`minimumRetentionDays`(必填, 不少于180天)、`archivalStrategy`(必填, 至少10个字符的长期策略描述)、`reviewer`(必填, 审核人)
- **出参**：`success`(布尔状态)、`plans`(长期存储策略列表)
- **用法描述**：提交长期业务数据的存储与归档策略。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-storage-cycle/plan/long-term -H "Content-Type: application/json" -d '{"datasetName":"customer_profile","minimumRetentionDays":730,"archivalStrategy":"每年归档一次并同步冷存储","reviewer":"admin"}'
    - **预期返回**：`success=true`，`plans`包含`strategyType=LONG_TERM`、`recommendedDays=730`的记录。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-storage-cycle/plan/long-term -H "Content-Type: application/json" -d '{"datasetName":"customer_profile","minimumRetentionDays":365,"archivalStrategy":"半年归档一次并保留热备","reviewer":"security"}'
    - **预期返回**：`success=true`，`plans`列表按创建时间倒序展示两条长期策略，最近一次`recommendedDays=365`排首位。
  - ❌ 示例：curl -X POST http://localhost:8080/api/data-storage-cycle/plan/long-term -H "Content-Type: application/json" -d '{"datasetName":"customer_profile","minimumRetentionDays":30,"archivalStrategy":"短策略","reviewer":""}'
    - **预期返回**：HTTP 500，错误信息提示保留天数需≥180且策略描述、审核人不能为空。

## 5. 分类管理查询语句 `/api/slow-sql/classify`
- **入参**：`categoryName`(必填, 慢SQL分类名称)、`sampleSql`(必填, 示例SQL不少于10个字符)、`optimizationSuggestion`(可选, 调优建议)、`expectedGain`(必填, 0-100之间的收益预估)
- **出参**：`success`(布尔状态)、`queries`(分类后的慢SQL样例列表)
- **用法描述**：登记慢SQL分类与优化建议，供运维查询。
  - ✅ 示例：curl -X POST http://localhost:8080/api/slow-sql/classify -H "Content-Type: application/json" -d '{"categoryName":"缺索引查询","sampleSql":"SELECT * FROM orders WHERE customer_id = ?","optimizationSuggestion":"增加customer_id索引","expectedGain":60}'
    - **预期返回**：`success=true`，`queries`列表中新增`categoryName=缺索引查询`的慢SQL记录，含收益 60。
  - ✅ 示例：curl -X POST http://localhost:8080/api/slow-sql/classify -H "Content-Type: application/json" -d '{"categoryName":"跨分区扫描","sampleSql":"SELECT * FROM event_log WHERE day BETWEEN ? AND ?","expectedGain":45}'
    - **预期返回**：`success=true`，`queries`列表包含`跨分区扫描`条目，未提供调优建议字段时返回空值。
  - ❌ 示例：curl -X POST http://localhost:8080/api/slow-sql/classify -H "Content-Type: application/json" -d '{"categoryName":"","sampleSql":"select 1","expectedGain":200}'
    - **预期返回**：HTTP 500，错误信息指出分类名称不能为空且收益需在 0-100 之间。

## 6. 数据库结构调整策略 `/api/slow-sql/structure`
- **入参**：`strategyName`(必填, 调整策略名称)、`adjustmentDetail`(必填, 详细策略不少于10个字符)、`expectedImpact`(必填, 预期影响说明)、`proposer`(可选, 提出人)
- **出参**：`success`(布尔状态)、`strategies`(结构调整策略清单)
- **用法描述**：登记数据库结构调整建议，持续完善优化规则库。
  - ✅ 示例：curl -X POST http://localhost:8080/api/slow-sql/structure -H "Content-Type: application/json" -d '{"strategyName":"订单表分区","adjustmentDetail":"按照月份新增HASH分区，提升写入效率","expectedImpact":"减少写入锁等待","proposer":"ops"}'
    - **预期返回**：`success=true`，`strategies`包含`strategyName=订单表分区`的最新策略记录。
  - ✅ 示例：curl -X POST http://localhost:8080/api/slow-sql/structure -H "Content-Type: application/json" -d '{"strategyName":"索引重建","adjustmentDetail":"每周日对活跃交易表重建索引","expectedImpact":"降低慢查询次数"}'
    - **预期返回**：`success=true`，`strategies`列表展示`索引重建`策略，并保留历史策略。
  - ❌ 示例：curl -X POST http://localhost:8080/api/slow-sql/structure -H "Content-Type: application/json" -d '{"strategyName":"","adjustmentDetail":"简单调优","expectedImpact":""}'
    - **预期返回**：HTTP 500，提示策略名称和影响说明不能为空且调整明细需满足长度要求。

## 7. 建立数据库监控记录 `/api/slow-sql/monitoring`
- **入参**：`monitorDate`(必填, yyyy-MM-dd 格式的监控日期)、`slowQueryCount`(必填, 慢查询数量≥0)、`maxResponseTime`(必填, 最大响应时间>0, 单位毫秒)、`notes`(可选, 备注)
- **出参**：`success`(布尔状态)、`records`(近期待监控记录列表)
- **用法描述**：记录每日慢SQL监控摘要，便于排查。
  - ✅ 示例：curl -X POST http://localhost:8080/api/slow-sql/monitoring -H "Content-Type: application/json" -d '{"monitorDate":"2024-05-01","slowQueryCount":23,"maxResponseTime":4500,"notes":"凌晨批处理波动"}'
    - **预期返回**：`success=true`，`records`中包含 2024-05-01 的监控数据，慢查询数量 23。
  - ✅ 示例：curl -X POST http://localhost:8080/api/slow-sql/monitoring -H "Content-Type: application/json" -d '{"monitorDate":"2024-05-02","slowQueryCount":10,"maxResponseTime":3800}'
    - **预期返回**：`success=true`，`records`列表含 5 月 2 日新记录，按日期倒序排列 5 月 2 日在前。
  - ❌ 示例：curl -X POST http://localhost:8080/api/slow-sql/monitoring -H "Content-Type: application/json" -d '{"monitorDate":"","slowQueryCount":-1,"maxResponseTime":0}'
    - **预期返回**：HTTP 500，提示日期格式非法、慢查询数量与响应时间必须为正。

## 8. 数据权威源目录清单 `/api/data-source-management/authority-catalog`
- **入参**：`domainName`(必填, 数据域名称)、`owner`(可选, 权威源负责人)、`pageNo`(必填, 页码>0)、`pageSize`(必填, 1-200 范围内的分页大小)
- **出参**：`success`(布尔状态)、`total`(目录条目总数)、`entries`(权源条目列表)
- **用法描述**：分页查询指定领域的数据权威源及负责人。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-source-management/authority-catalog -H "Content-Type: application/json" -d '{"domainName":"营销域","owner":"marketing_team","pageNo":1,"pageSize":20}'
    - **预期返回**：`success=true`，`total`显示营销域的权威源数量，`entries`返回负责人为`marketing_team`的条目列表。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-source-management/authority-catalog -H "Content-Type: application/json" -d '{"domainName":"营销域","pageNo":1,"pageSize":5}'
    - **预期返回**：`success=true`，`entries`分页返回前 5 条权威源记录，`total`保持全部数量。
  - ❌ 示例：curl -X POST http://localhost:8080/api/data-source-management/authority-catalog -H "Content-Type: application/json" -d '{"domainName":"营销域","pageNo":0,"pageSize":300}'
    - **预期返回**：HTTP 500，提示页码需大于 0 且分页大小必须在 1-200 之间。

## 9. 营销指标的数据来源追溯 `/api/data-source-management/indicator-trace`
- **入参**：`indicatorCode`(可选, 指标编码)、`indicatorName`(可选, 指标名称)、`traceDepth`(可选, 血缘追溯深度说明)
- **出参**：`success`(布尔状态)、`lineage`(指标血缘节点列表)
- **用法描述**：检索营销指标的来源表字段及血缘路径。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-source-management/indicator-trace -H "Content-Type: application/json" -d '{"indicatorCode":"MKT_CONV_RATE","traceDepth":"DEEP"}'
    - **预期返回**：`success=true`，`lineage`展示`MKT_CONV_RATE`指标的来源表、字段及血缘深度。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-source-management/indicator-trace -H "Content-Type: application/json" -d '{"indicatorName":"月度投放ROI"}'
    - **预期返回**：`success=true`，`lineage`根据指标名称返回对应血缘链路。
  - ❌ 示例：curl -X POST http://localhost:8080/api/data-source-management/indicator-trace -H "Content-Type: application/json" -d '{"indicatorCode":"","indicatorName":""}'
    - **预期返回**：HTTP 500，提示指标编码和名称不能同时为空。

## 10. 权限管理—授权的管理员用户才能进行数据资产清单维护、数据血缘关系等功能管理和修改操作 `/api/data-responsibility/admin-permission`
- **入参**：`adminUserId`(必填, 管理员用户ID>0)、`permissionType`(必填, 授权的权限类型)、`grantedBy`(必填, 授权人)、`expireAt`(可选, 授权失效时间yyyy-MM-dd HH:mm:ss)
- **出参**：`success`(布尔状态)、`permissions`(最新管理员授权记录列表)
- **用法描述**：记录管理员的权限授权信息。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-responsibility/admin-permission -H "Content-Type: application/json" -d '{"adminUserId":1001,"permissionType":"ASSET_MAINTAIN","grantedBy":"system","expireAt":"2025-12-31 23:59:59"}'
    - **预期返回**：`success=true`，`permissions`列表包含用户 1001 的授权记录，含失效时间 2025-12-31。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-responsibility/admin-permission -H "Content-Type: application/json" -d '{"adminUserId":1002,"permissionType":"LINEAGE_EDIT","grantedBy":"security"}'
    - **预期返回**：`success=true`，`permissions`展示用户 1002 的授权条目，并保留历史授权。
  - ❌ 示例：curl -X POST http://localhost:8080/api/data-responsibility/admin-permission -H "Content-Type: application/json" -d '{"adminUserId":0,"permissionType":"","grantedBy":""}'
    - **预期返回**：HTTP 500，提示管理员 ID 必须大于 0 且权限类型、授权人不可为空。

## 11. 权限管理—普通用户其他可以根据业务对应关系授权是否可查、引用数据血缘相关内容 `/api/data-responsibility/user-access`
- **入参**：`userId`(必填, 普通用户ID>0)、`businessRelation`(必填, 授权所依据的业务关系)、`accessScope`(必填, 可访问的数据范围)、`approver`(必填, 审批人)
- **出参**：`success`(布尔状态)、`accesses`(该业务关系下的授权记录列表)
- **用法描述**：登记普通用户对血缘内容的授权范围。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-responsibility/user-access -H "Content-Type: application/json" -d '{"userId":2008,"businessRelation":"渠道运营","accessScope":"READ_ONLY","approver":"admin"}'
    - **预期返回**：`success=true`，`accesses`列表新增用户 2008 的只读授权。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-responsibility/user-access -H "Content-Type: application/json" -d '{"userId":2010,"businessRelation":"活动对接","accessScope":"READ_WRITE","approver":"ops_lead"}'
    - **预期返回**：`success=true`，`accesses`展示用户 2010 的读写授权记录。
  - ❌ 示例：curl -X POST http://localhost:8080/api/data-responsibility/user-access -H "Content-Type: application/json" -d '{"userId":0,"businessRelation":"","accessScope":"","approver":""}'
    - **预期返回**：HTTP 500，提示用户 ID、业务关系、访问范围与审批人均不可为空。

## 12. 数据加密 `/api/data-security/encryption`
- **入参**：`datasetName`(必填, 需加密的数据集)、`algorithm`(必填, 加密算法标识不少于3个字符)、`keyIdentifier`(必填, 密钥标识不少于4个字符)、`enabled`(必填, 是否启用)
- **出参**：`success`(布尔状态)、`policy`(新增或更新的加密策略详情)
- **用法描述**：配置或更新数据集的加密策略。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-security/encryption -H "Content-Type: application/json" -d '{"datasetName":"customer_profile","algorithm":"AES256","keyIdentifier":"kms-key-01","enabled":true}'
    - **预期返回**：`success=true`，`policy`返回`datasetName=customer_profile`、`algorithm=AES256`且启用状态为 true。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-security/encryption -H "Content-Type: application/json" -d '{"datasetName":"campaign_fact","algorithm":"SM4","keyIdentifier":"kms-key-02","enabled":false}'
    - **预期返回**：`success=true`，`policy`显示`campaign_fact`策略已禁用，密钥标识为`kms-key-02`。
  - ❌ 示例：curl -X POST http://localhost:8080/api/data-security/encryption -H "Content-Type: application/json" -d '{"datasetName":"","algorithm":"A","keyIdentifier":"k1"}'
    - **预期返回**：HTTP 500，提示数据集、算法、密钥标识和启用状态必须满足长度和必填要求。

## 13. 权限控制 `/api/data-security/permission`
- **入参**：`roleName`(必填, 角色名称)、`resourceName`(必填, 数据资源名称)、`accessLevel`(必填, 权限级别描述)、`approvedBy`(必填, 审批人)
- **出参**：`success`(布尔状态)、`policies`(最新的权限策略列表)
- **用法描述**：为角色配置数据集访问级别。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-security/permission -H "Content-Type: application/json" -d '{"roleName":"marketing-analyst","resourceName":"campaign_fact","accessLevel":"READ","approvedBy":"security_lead"}'
    - **预期返回**：`success=true`，`policies`列表新增角色`marketing-analyst`对`campaign_fact`的 READ 权限。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-security/permission -H "Content-Type: application/json" -d '{"roleName":"ops-engineer","resourceName":"customer_profile","accessLevel":"WRITE","approvedBy":"cto"}'
    - **预期返回**：`success=true`，`policies`中记录`ops-engineer`对`customer_profile`的 WRITE 权限。
  - ❌ 示例：curl -X POST http://localhost:8080/api/data-security/permission -H "Content-Type: application/json" -d '{"roleName":"","resourceName":"","accessLevel":"","approvedBy":""}'
    - **预期返回**：HTTP 500，提示角色、资源、访问级别及审批人均为必填项。

## 14. 数据脱敏 `/api/data-security/masking`
- **入参**：`datasetName`(必填, 脱敏目标数据集)、`fieldName`(必填, 脱敏字段)、`maskingType`(必填, 脱敏方式)、`previewValue`(可选, 脱敏示例)
- **出参**：`success`(布尔状态)、`rules`(该数据集的脱敏规则列表)
- **用法描述**：登记或更新字段脱敏策略。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-security/masking -H "Content-Type: application/json" -d '{"datasetName":"customer_profile","fieldName":"mobile","maskingType":"MASK_MIDDLE","previewValue":"138****5678"}'
    - **预期返回**：`success=true`，`rules`列表包含`fieldName=mobile`、`maskingType=MASK_MIDDLE`的新规则。
  - ✅ 示例：curl -X POST http://localhost:8080/api/data-security/masking -H "Content-Type: application/json" -d '{"datasetName":"customer_profile","fieldName":"id_card","maskingType":"MASK_LEFT","previewValue":"****1234"}'
    - **预期返回**：`success=true`，`rules`展示`id_card`字段的脱敏配置，并保留已有条目。
  - ❌ 示例：curl -X POST http://localhost:8080/api/data-security/masking -H "Content-Type: application/json" -d '{"datasetName":"","fieldName":"","maskingType":"**"}'
    - **预期返回**：HTTP 500，提示数据集、字段与脱敏方式不可为空且脱敏方式需合法。

## 15. 查询分析指定历史时点的营销业务指标 `/api/indicator-monitoring/history`
- **入参**：`indicatorCode`(必填, 指标编码)、`startDate`(必填, 查询起始日期yyyy-MM-dd)、`endDate`(必填, 查询结束日期yyyy-MM-dd)
- **出参**：`success`(布尔状态)、`snapshots`(历史指标快照列表)
- **用法描述**：查询指标在指定时间段的历史快照。
  - ✅ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/history -H "Content-Type: application/json" -d '{"indicatorCode":"MKT_CONV_RATE","startDate":"2024-05-01","endDate":"2024-05-31"}'
    - **预期返回**：`success=true`，`snapshots`返回 5 月份内的每日指标值，包含`2024-05-31`等记录。
  - ✅ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/history -H "Content-Type: application/json" -d '{"indicatorCode":"MKT_IMPRESSION","startDate":"2024-06-01","endDate":"2024-06-07"}'
    - **预期返回**：`success=true`，`snapshots`列出曝光指标在 6 月首周的数值。
  - ❌ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/history -H "Content-Type: application/json" -d '{"indicatorCode":"","startDate":"","endDate":""}'
    - **预期返回**：HTTP 500，提示指标编码与起止日期均为必填且需合法。

## 16. 业务指标趋势变化分析-日 `/api/indicator-monitoring/trend/day`
- **入参**：`indicatorCode`(必填, 指标编码)、`statDate`(必填, 统计日期yyyy-MM-dd)
- **出参**：`success`(布尔状态)、`trend`(日度趋势详情)
- **用法描述**：查看指标在指定日的趋势表现。
  - ✅ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/trend/day -H "Content-Type: application/json" -d '{"indicatorCode":"MKT_CONV_RATE","statDate":"2024-05-31"}'
    - **预期返回**：`success=true`，`trend`提供 2024-05-31 的转化率、同比/环比趋势描述。
  - ✅ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/trend/day -H "Content-Type: application/json" -d '{"indicatorCode":"MKT_IMPRESSION","statDate":"2024-06-10"}'
    - **预期返回**：`success=true`，`trend`返回曝光量指标在 6 月 10 日的趋势数据。
  - ❌ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/trend/day -H "Content-Type: application/json" -d '{"indicatorCode":"","statDate":"2024/06/10"}'
    - **预期返回**：HTTP 500，提示指标编码不能为空且日期格式必须为 yyyy-MM-dd。

## 17. 业务指标趋势变化分析-周 `/api/indicator-monitoring/trend/week`
- **入参**：`indicatorCode`(必填, 指标编码)、`weekOfYear`(必填, 周次yyyy-ww)
- **出参**：`success`(布尔状态)、`trend`(周度趋势详情)
- **用法描述**：查看指标在指定周的趋势表现。
  - ✅ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/trend/week -H "Content-Type: application/json" -d '{"indicatorCode":"MKT_CONV_RATE","weekOfYear":"2024-22"}'
    - **预期返回**：`success=true`，`trend`展示 2024 年第 22 周的周度统计与周同比信息。
  - ✅ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/trend/week -H "Content-Type: application/json" -d '{"indicatorCode":"MKT_IMPRESSION","weekOfYear":"2024-24"}'
    - **预期返回**：`success=true`，`trend`给出对应周的曝光趋势图表数据。
  - ❌ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/trend/week -H "Content-Type: application/json" -d '{"indicatorCode":"MKT_CONV_RATE","weekOfYear":"24-06"}'
    - **预期返回**：HTTP 500，提示周次格式应为 yyyy-ww。

## 18. 业务指标趋势变化分析-月 `/api/indicator-monitoring/trend/month`
- **入参**：`indicatorCode`(必填, 指标编码)、`month`(必填, yyyy-MM)
- **出参**：`success`(布尔状态)、`trend`(月度趋势详情)
- **用法描述**：查看指标在指定月份的趋势表现。
  - ✅ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/trend/month -H "Content-Type: application/json" -d '{"indicatorCode":"MKT_CONV_RATE","month":"2024-05"}'
    - **预期返回**：`success=true`，`trend`总结 2024 年 5 月的转化率走势，含环比指标。
  - ✅ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/trend/month -H "Content-Type: application/json" -d '{"indicatorCode":"MKT_IMPRESSION","month":"2024-06"}'
    - **预期返回**：`success=true`，`trend`提供 2024 年 6 月曝光指标的月趋势数据。
  - ❌ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/trend/month -H "Content-Type: application/json" -d '{"indicatorCode":"","month":"202406"}'
    - **预期返回**：HTTP 500，提示指标编码不可为空且月份格式需为 yyyy-MM。

## 19. 业务指标与历史数据的对比-同比 `/api/indicator-monitoring/compare/yoy`
- **入参**：`indicatorCode`(必填, 指标编码)、`targetDate`(必填, 对比日期yyyy-MM-dd)
- **出参**：`success`(布尔状态)、`targetDate`(当日)、`compareDate`(去年同日)、`currentValue`(当日值)、`historyValue`(去年今日值)、`changeRate`(同比变化率)
- **用法描述**：比较当日指标与去年同日的数据表现。
  - ✅ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/compare/yoy -H "Content-Type: application/json" -d '{"indicatorCode":"MKT_CONV_RATE","targetDate":"2024-05-31"}'
    - **预期返回**：`success=true`，`compareDate`自动计算为`2023-05-31`，返回两日数值及同比增幅。
  - ✅ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/compare/yoy -H "Content-Type: application/json" -d '{"indicatorCode":"MKT_IMPRESSION","targetDate":"2024-06-10"}'
    - **预期返回**：`success=true`，`compareDate=2023-06-10`，显示曝光量的同比变化率。
  - ❌ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/compare/yoy -H "Content-Type: application/json" -d '{"indicatorCode":"","targetDate":"2024/06/10"}'
    - **预期返回**：HTTP 500，提示指标编码必填且日期格式不正确。

## 20. 业务指标与历史数据的对比-环比 `/api/indicator-monitoring/compare/mom`
- **入参**：`indicatorCode`(必填, 指标编码)、`targetDate`(必填, 对比日期yyyy-MM-dd)
- **出参**：`success`(布尔状态)、`targetDate`(当日)、`compareDate`(昨日)、`currentValue`(当日值)、`historyValue`(昨日值)、`changeRate`(环比变化率)
- **用法描述**：比较当日指标与昨日的数据表现。
  - ✅ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/compare/mom -H "Content-Type: application/json" -d '{"indicatorCode":"MKT_CONV_RATE","targetDate":"2024-05-31"}'
    - **预期返回**：`success=true`，`compareDate`自动计算为`2024-05-30`，返回当日与昨日值及环比增幅。
  - ✅ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/compare/mom -H "Content-Type: application/json" -d '{"indicatorCode":"MKT_IMPRESSION","targetDate":"2024-06-10"}'
    - **预期返回**：`success=true`，`compareDate=2024-06-09`，展示曝光量的环比变化率。
  - ❌ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/compare/mom -H "Content-Type: application/json" -d '{"indicatorCode":"","targetDate":"2024/06/10"}'
    - **预期返回**：HTTP 500，提示指标编码必填且日期格式错误。

## 21. 营销业务指标实时监控分析-指标分类维度 `/api/indicator-monitoring/realtime`
- **入参**：`dimension`(必填, 指标分类维度，支持指标分类、实时数据、关键指标、异常告警、责任主体、应用范围、系统调用、关注度)、`pageNo`(必填, >0)、`pageSize`(必填, 1-200)
- **出参**：`success`(布尔状态)、`dimension`(查询维度)、`pageNo`、`pageSize`、`total`、`records`(实时监控结果)
- **用法描述**：按指定维度查看实时监控列表。
  - ✅ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/realtime -H "Content-Type: application/json" -d '{"dimension":"指标分类","pageNo":1,"pageSize":10}'
    - **预期返回**：`success=true`，返回指标分类维度的监控记录，含分页信息。
  - ✅ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/realtime -H "Content-Type: application/json" -d '{"dimension":"异常检测告警","pageNo":1,"pageSize":5}'
    - **预期返回**：`success=true`，`records`聚焦异常告警维度的实时指标。
  - ❌ 示例：curl -X POST http://localhost:8080/api/indicator-monitoring/realtime -H "Content-Type: application/json" -d '{"dimension":"未知维度","pageNo":0,"pageSize":500}'
    - **预期返回**：HTTP 500，提示维度非法且分页参数不合法。

## 22. 营销标签应用情况监控分析-标签应用数量 `/api/tag-monitoring/usage-count`
- **入参**：`tagCategory`(必填, 标签类别)、`dateRange`(必填, 查询区间，格式yyyy-MM-dd~yyyy-MM-dd)、`pageNo`(必填, >0)、`pageSize`(必填, 1-200)
- **出参**：`success`(布尔状态)、`summary`(数量统计概览)、`records`(标签使用记录列表)、`pageNo`、`pageSize`、`total`
- **用法描述**：统计各标签在指定时间范围内的使用次数。
  - ✅ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/usage-count -H "Content-Type: application/json" -d '{"tagCategory":"客户画像","dateRange":"2024-05-01~2024-05-31","pageNo":1,"pageSize":10}'
    - **预期返回**：`success=true`，`summary`给出客户画像标签的总调用次数，`records`列出每个标签的具体使用量。
  - ✅ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/usage-count -H "Content-Type: application/json" -d '{"tagCategory":"渠道行为","dateRange":"2024-06-01~2024-06-30","pageNo":2,"pageSize":5}'
    - **预期返回**：`success=true`，分页第二页展示渠道行为标签的使用明细。
  - ❌ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/usage-count -H "Content-Type: application/json" -d '{"tagCategory":"客户画像","dateRange":"2024/05/01-2024/05/31","pageNo":0,"pageSize":500}'
    - **预期返回**：HTTP 500，提示日期区间格式错误且分页参数不合法。

## 23. 营销标签应用情况监控分析-标签应用使用频率 `/api/tag-monitoring/usage-frequency`
- **入参**：`tagName`(必填, 标签名称)、`statGranularity`(必填, 统计粒度DAILY/WEEKLY/MONTHLY)、`startDate`(必填)、`endDate`(必填)
- **出参**：`success`(布尔状态)、`frequencySeries`(频率时间序列)
- **用法描述**：分析指定标签在时间区间内的调用频率。
  - ✅ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/usage-frequency -H "Content-Type: application/json" -d '{"tagName":"高价值客户","statGranularity":"DAILY","startDate":"2024-05-01","endDate":"2024-05-31"}'
    - **预期返回**：`success=true`，`frequencySeries`提供每日使用频率序列。
  - ✅ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/usage-frequency -H "Content-Type: application/json" -d '{"tagName":"高价值客户","statGranularity":"WEEKLY","startDate":"2024-04-01","endDate":"2024-06-30"}'
    - **预期返回**：`success=true`，周度粒度的频率结果返回 4-6 月各周使用次数。
  - ❌ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/usage-frequency -H "Content-Type: application/json" -d '{"tagName":"","statGranularity":"YEARLY","startDate":"2024-05-01","endDate":"2024-05-31"}'
    - **预期返回**：HTTP 500，提示标签名必填且统计粒度需为 DAILY/WEEKLY/MONTHLY 之一。

## 24. 营销标签应用情况监控分析-标签应用影响范围 `/api/tag-monitoring/impact-scope`
- **入参**：`tagName`(必填, 标签名称)、`businessScene`(可选, 应用场景)、`pageNo`(必填, >0)、`pageSize`(必填, 1-200)
- **出参**：`success`(布尔状态)、`impactSummary`(影响范围概览)、`records`(影响详情列表)、`pageNo`、`pageSize`、`total`
- **用法描述**：查看标签对触达人群、渠道的影响范围。
  - ✅ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/impact-scope -H "Content-Type: application/json" -d '{"tagName":"高价值客户","businessScene":"短信触达","pageNo":1,"pageSize":10}'
    - **预期返回**：`success=true`，`impactSummary`显示短信触达场景的受众规模，`records`列出相关渠道。
  - ✅ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/impact-scope -H "Content-Type: application/json" -d '{"tagName":"流失预警","pageNo":1,"pageSize":5}'
    - **预期返回**：`success=true`，返回流失预警标签在各业务场景下的覆盖范围。
  - ❌ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/impact-scope -H "Content-Type: application/json" -d '{"tagName":"","pageNo":0,"pageSize":500}'
    - **预期返回**：HTTP 500，提示标签名称必填且分页参数不合法。

## 25. 实时监控标签应用情况和效果-标签应用触发情况 `/api/tag-monitoring/trigger-status`
- **入参**：`tagName`(必填, 标签名称)、`startTime`(必填, yyyy-MM-dd HH:mm:ss)、`endTime`(必填, yyyy-MM-dd HH:mm:ss)
- **出参**：`success`(布尔状态)、`triggerLogs`(触发日志列表)
- **用法描述**：实时查看标签触发次数与时间分布。
  - ✅ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/trigger-status -H "Content-Type: application/json" -d '{"tagName":"高价值客户","startTime":"2024-05-01 00:00:00","endTime":"2024-05-01 23:59:59"}'
    - **预期返回**：`success=true`，`triggerLogs`列出 5 月 1 日内的触发明细与渠道。
  - ✅ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/trigger-status -H "Content-Type: application/json" -d '{"tagName":"流失预警","startTime":"2024-05-10 08:00:00","endTime":"2024-05-10 20:00:00"}'
    - **预期返回**：`success=true`，返回流失预警标签在指定时段的触发记录。
  - ❌ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/trigger-status -H "Content-Type: application/json" -d '{"tagName":"高价值客户","startTime":"2024-05-01","endTime":"2024-05-01"}'
    - **预期返回**：HTTP 500，提示时间需包含时分秒并且结束时间必须大于开始时间。

## 26. 实时监控标签应用情况和效果-标签应用应用效果 `/api/tag-monitoring/effectiveness`
- **入参**：`tagName`(必填, 标签名称)、`evaluationWindow`(必填, yyyy-MM-dd~yyyy-MM-dd)、`effectMetric`(必填, 效果指标，如CTR、CVR)
- **出参**：`success`(布尔状态)、`analysis`(效果评估结果)
- **用法描述**：评估标签触达后的转化/反馈效果。
  - ✅ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/effectiveness -H "Content-Type: application/json" -d '{"tagName":"高价值客户","evaluationWindow":"2024-05-01~2024-05-31","effectMetric":"CVR"}'
    - **预期返回**：`success=true`，`analysis`提供 5 月份内的转化率、样本量及提升率。
  - ✅ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/effectiveness -H "Content-Type: application/json" -d '{"tagName":"唤醒沉睡用户","evaluationWindow":"2024-06-01~2024-06-15","effectMetric":"CTR"}'
    - **预期返回**：`success=true`，返回 CTR 指标的点击率表现。
  - ❌ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/effectiveness -H "Content-Type: application/json" -d '{"tagName":"高价值客户","evaluationWindow":"2024/05/01-2024/05/31","effectMetric":""}'
    - **预期返回**：HTTP 500，提示窗口格式需使用 ~ 分隔且效果指标必填。

## 27. 实时监控标签应用情况和效果-标签应用用户反馈 `/api/tag-monitoring/user-feedback`
- **入参**：`tagName`(必填, 标签名称)、`feedbackChannel`(必填, 反馈渠道，如APP、SMS)、`pageNo`(必填, >0)、`pageSize`(必填, 1-200)
- **出参**：`success`(布尔状态)、`feedbackSummary`(反馈概览)、`records`(反馈详情列表)、`pageNo`、`pageSize`、`total`
- **用法描述**：查看标签触达后的用户反馈内容。
  - ✅ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/user-feedback -H "Content-Type: application/json" -d '{"tagName":"高价值客户","feedbackChannel":"APP","pageNo":1,"pageSize":10}'
    - **预期返回**：`success=true`，`records`列出 APP 渠道的反馈，`feedbackSummary`统计满意度分布。
  - ✅ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/user-feedback -H "Content-Type: application/json" -d '{"tagName":"流失预警","feedbackChannel":"SMS","pageNo":1,"pageSize":5}'
    - **预期返回**：`success=true`，返回短信渠道反馈及关键词分析。
  - ❌ 示例：curl -X POST http://localhost:8080/api/tag-monitoring/user-feedback -H "Content-Type: application/json" -d '{"tagName":"","feedbackChannel":"APP","pageNo":0,"pageSize":500}'
    - **预期返回**：HTTP 500，提示标签名称必填且分页参数不合法。

## 28. 配置脱敏加密规则 `/api/sensitive-rules/masking-rule`
- **入参**：`ruleName`(必填, 规则名称)、`sensitiveType`(必填, 敏感数据类型)、`maskingStrategy`(必填, 脱敏策略JSON描述)、`policyReference`(必填, 制度依据描述)
- **出参**：`success`(布尔状态)、`rule`(新建的脱敏规则详情)
- **用法描述**：维护敏感数据脱敏规则及合规依据。
  - ✅ 示例：curl -X POST http://localhost:8080/api/sensitive-rules/masking-rule -H "Content-Type: application/json" -d '{"ruleName":"手机号遮蔽","sensitiveType":"MOBILE","maskingStrategy":{"type":"REPLACE","pattern":"***"},"policyReference":"数据安全规范第 12 条"}'
    - **预期返回**：`success=true`，`rule`展示`ruleName=手机号遮蔽`、`sensitiveType=MOBILE`及策略 JSON。
  - ✅ 示例：curl -X POST http://localhost:8080/api/sensitive-rules/masking-rule -H "Content-Type: application/json" -d '{"ruleName":"邮箱脱敏","sensitiveType":"EMAIL","maskingStrategy":{"type":"KEEP_DOMAIN"},"policyReference":"安全制度 3.2"}'
    - **预期返回**：`success=true`，返回邮箱脱敏规则并附带制度依据。
  - ❌ 示例：curl -X POST http://localhost:8080/api/sensitive-rules/masking-rule -H "Content-Type: application/json" -d '{"ruleName":"","sensitiveType":"","maskingStrategy":{},"policyReference":""}'
    - **预期返回**：HTTP 500，提示规则名称、敏感类型、策略和制度依据均不可为空。

## 29. 密钥生成 `/api/sensitive-rules/generate-key`
- **入参**：`algorithm`(必填, 算法标识)、`keyLength`(必填, 密钥长度，128/192/256)、`operator`(必填, 生成人员)
- **出参**：`success`(布尔状态)、`keyRecord`(生成记录详情)
- **用法描述**：记录敏感数据加密密钥生成流程。
  - ✅ 示例：curl -X POST http://localhost:8080/api/sensitive-rules/generate-key -H "Content-Type: application/json" -d '{"algorithm":"AES","keyLength":256,"operator":"security_bot"}'
    - **预期返回**：`success=true`，`keyRecord`返回生成的密钥标识、算法 AES、长度 256。
  - ✅ 示例：curl -X POST http://localhost:8080/api/sensitive-rules/generate-key -H "Content-Type: application/json" -d '{"algorithm":"SM4","keyLength":128,"operator":"sec_admin"}'
    - **预期返回**：`success=true`，生成记录显示算法 SM4、长度 128。
  - ❌ 示例：curl -X POST http://localhost:8080/api/sensitive-rules/generate-key -H "Content-Type: application/json" -d '{"algorithm":"AES","keyLength":512,"operator":""}'
    - **预期返回**：HTTP 500，提示密钥长度非法且操作人必填。

## 30. 密钥存储 `/api/sensitive-rules/store-key`
- **入参**：`keyIdentifier`(必填, 密钥标识)、`storageLocation`(必填, 存储位置描述)、`encryptionMethod`(必填, 存储加密方式)、`custodian`(必填, 保管人)
- **出参**：`success`(布尔状态)、`storageRecord`(存储记录详情)
- **用法描述**：登记加密密钥的存储位置及保护方式。
  - ✅ 示例：curl -X POST http://localhost:8080/api/sensitive-rules/store-key -H "Content-Type: application/json" -d '{"keyIdentifier":"kms-key-01","storageLocation":"HSM-Vault-A1","encryptionMethod":"HSM_WRAP","custodian":"vault_admin"}'
    - **预期返回**：`success=true`，`storageRecord`显示密钥`kms-key-01`已存入`HSM-Vault-A1`，采用 HSM_WRAP。
  - ✅ 示例：curl -X POST http://localhost:8080/api/sensitive-rules/store-key -H "Content-Type: application/json" -d '{"keyIdentifier":"kms-key-02","storageLocation":"SecureRoom-B2","encryptionMethod":"AES256","custodian":"sec_ops"}'
    - **预期返回**：`success=true`，存储记录展示`kms-key-02`的保管信息。
  - ❌ 示例：curl -X POST http://localhost:8080/api/sensitive-rules/store-key -H "Content-Type: application/json" -d '{"keyIdentifier":"","storageLocation":"","encryptionMethod":"","custodian":""}'
    - **预期返回**：HTTP 500，提示密钥标识、存储位置、加密方式和保管人均需填写。

## 31. 密钥分发 `/api/sensitive-rules/distribute-key`
- **入参**：`keyIdentifier`(必填, 密钥标识)、`targetSystem`(必填, 目标系统)、`distributionMethod`(必填, 分发方式)、`operator`(必填, 分发人员)
- **出参**：`success`(布尔状态)、`distributionRecord`(分发记录详情)
- **用法描述**：记录密钥分发的系统、方式与责任人。
  - ✅ 示例：curl -X POST http://localhost:8080/api/sensitive-rules/distribute-key -H "Content-Type: application/json" -d '{"keyIdentifier":"kms-key-01","targetSystem":"营销标签系统","distributionMethod":"API_PULL","operator":"sec_ops"}'
    - **预期返回**：`success=true`，`distributionRecord`显示密钥`kms-key-01`已通过 API_PULL 分发至营销标签系统。
  - ✅ 示例：curl -X POST http://localhost:8080/api/sensitive-rules/distribute-key -H "Content-Type: application/json" -d '{"keyIdentifier":"kms-key-02","targetSystem":"BI平台","distributionMethod":"MANUAL_IMPORT","operator":"sec_admin"}'
    - **预期返回**：`success=true`，记录 BI 平台的手工导入分发。
  - ❌ 示例：curl -X POST http://localhost:8080/api/sensitive-rules/distribute-key -H "Content-Type: application/json" -d '{"keyIdentifier":"","targetSystem":"","distributionMethod":"","operator":""}'
    - **预期返回**：HTTP 500，提示所有字段均为必填。

## 32. 密钥轮换 `/api/sensitive-rules/rotate-key`
- **入参**：`keyIdentifier`(必填, 密钥标识)、`rotationReason`(必填, 轮换原因)、`scheduledDate`(必填, 计划日期yyyy-MM-dd)、`operator`(必填, 轮换负责人)
- **出参**：`success`(布尔状态)、`rotationRecord`(轮换记录详情)
- **用法描述**：记录密钥轮换计划与原因。
  - ✅ 示例：curl -X POST http://localhost:8080/api/sensitive-rules/rotate-key -H "Content-Type: application/json" -d '{"keyIdentifier":"kms-key-01","rotationReason":"年度例行轮换","scheduledDate":"2024-12-01","operator":"sec_ops"}'
    - **预期返回**：`success=true`，`rotationRecord`显示轮换日期 2024-12-01 及原因。
  - ✅ 示例：curl -X POST http://localhost:8080/api/sensitive-rules/rotate-key -H "Content-Type: application/json" -d '{"keyIdentifier":"kms-key-02","rotationReason":"密钥疑似泄露","scheduledDate":"2024-07-15","operator":"sec_admin"}'
    - **预期返回**：`success=true`，记录紧急轮换计划。
  - ❌ 示例：curl -X POST http://localhost:8080/api/sensitive-rules/rotate-key -H "Content-Type: application/json" -d '{"keyIdentifier":"kms-key-03","rotationReason":"短","scheduledDate":"2024/07/15","operator":""}'
    - **预期返回**：HTTP 500，提示轮换原因需不少于 5 个字符，日期格式需正确且负责人必填。

## 33. 添加校验规则到规则库 `/api/validation-rules/add`
- **入参**：`ruleCode`(必填, 规则编码)、`ruleName`(必填, 规则名称)、`ruleLogic`(必填, 规则逻辑描述)、`severity`(必填, 严重级别HIGH/MEDIUM/LOW)、`createdBy`(必填, 创建人)
- **出参**：`success`(布尔状态)、`rule`(新增规则详情)
- **用法描述**：向校验规则库中新增数据质量规则。
  - ✅ 示例：curl -X POST http://localhost:8080/api/validation-rules/add -H "Content-Type: application/json" -d '{"ruleCode":"RULE_DUPLICATE_CHECK","ruleName":"重复校验","ruleLogic":"检查主键重复","severity":"HIGH","createdBy":"qa"}'
    - **预期返回**：`success=true`，`rule`返回新增规则的完整信息，严重级别为 HIGH。
  - ✅ 示例：curl -X POST http://localhost:8080/api/validation-rules/add -H "Content-Type: application/json" -d '{"ruleCode":"RULE_NULL_CHECK","ruleName":"空值校验","ruleLogic":"字段不能为空","severity":"MEDIUM","createdBy":"data_dev"}'
    - **预期返回**：`success=true`，记录空值校验规则并返回规则详情。
  - ❌ 示例：curl -X POST http://localhost:8080/api/validation-rules/add -H "Content-Type: application/json" -d '{"ruleCode":"","ruleName":"","ruleLogic":"","severity":"CRITICAL","createdBy":""}'
    - **预期返回**：HTTP 500，提示编码、名称、逻辑、严重级别和创建人需合法填写。

## 34. 查询校验规则 `/api/validation-rules/query`
- **入参**：`keyword`(可选, 关键字)、`severity`(可选, 严重级别过滤)、`pageNo`(必填, >0)、`pageSize`(必填, 1-200)
- **出参**：`success`(布尔状态)、`total`(符合条件的规则数量)、`records`(规则列表)、`pageNo`、`pageSize`
- **用法描述**：按关键字与严重程度筛选校验规则。
  - ✅ 示例：curl -X POST http://localhost:8080/api/validation-rules/query -H "Content-Type: application/json" -d '{"keyword":"校验","severity":"HIGH","pageNo":1,"pageSize":10}'
    - **预期返回**：`success=true`，`records`返回严重级别为 HIGH 且名称含“校验”的规则列表。
  - ✅ 示例：curl -X POST http://localhost:8080/api/validation-rules/query -H "Content-Type: application/json" -d '{"pageNo":1,"pageSize":5}'
    - **预期返回**：`success=true`，分页展示所有规则的前 5 条记录。
  - ❌ 示例：curl -X POST http://localhost:8080/api/validation-rules/query -H "Content-Type: application/json" -d '{"pageNo":0,"pageSize":500}'
    - **预期返回**：HTTP 500，提示页码与分页大小不合法。

## 35. 删除校验规则 `/api/validation-rules/delete`
- **入参**：`ruleId`(必填, 规则主键ID>0)、`deletedBy`(必填, 删除人)
- **出参**：`success`(布尔状态)、`deleted`(删除结果布尔值)
- **用法描述**：根据主键删除指定的校验规则。
  - ✅ 示例：curl -X POST http://localhost:8080/api/validation-rules/delete -H "Content-Type: application/json" -d '{"ruleId":1,"deletedBy":"qa"}'
    - **预期返回**：`success=true`，`deleted=true` 表示规则已删除。
  - ✅ 示例：curl -X POST http://localhost:8080/api/validation-rules/delete -H "Content-Type: application/json" -d '{"ruleId":999,"deletedBy":"qa"}'
    - **预期返回**：`success=true`，`deleted=false` 表示未找到对应规则。
  - ❌ 示例：curl -X POST http://localhost:8080/api/validation-rules/delete -H "Content-Type: application/json" -d '{"ruleId":0,"deletedBy":""}'
    - **预期返回**：HTTP 500，提示规则 ID 必须大于 0 且删除人不能为空。
