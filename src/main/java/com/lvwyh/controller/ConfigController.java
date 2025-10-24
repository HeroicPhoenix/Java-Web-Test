package com.lvwyh.controller;

import com.lvwyh.service.ConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config")
@Tag(name = "配置导出", description = "下载系统配置文件")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @Operation(summary = "导出配置数据为 SQLite 文件")
    @GetMapping("/export")
    public ResponseEntity<byte[]> export() {
        byte[] data = configService.exportConfigDatabase();
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"data.db\"")
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .contentLength(data.length)
                .body(data);
    }
}
