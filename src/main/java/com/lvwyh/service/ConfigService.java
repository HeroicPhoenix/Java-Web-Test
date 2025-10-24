package com.lvwyh.service;

public interface ConfigService {
    /**
     * 导出配置类数据为 SQLite 数据库文件。
     *
     * @return data.db 文件的字节数组
     */
    byte[] exportConfigDatabase();
}
