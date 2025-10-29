package java.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.dto.TagRequest;
import java.entity.TagUsageRecord;
import java.service.TagService;
import java.vo.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

    @Operation(summary = "营销标签应用情况监控分析-标签应用数量")
    @PostMapping("/count")
    public ResponseEntity<CommonResponse<TagUsageRecord>> count(@RequestBody TagRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Tag code is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.recordUsageCount(request)));
    }

    @Operation(summary = "营销标签应用情况监控分析-标签应用使用频率")
    @PostMapping("/frequency")
    public ResponseEntity<CommonResponse<java.util.List<TagUsageRecord>>> frequency(@RequestBody TagRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Tag code is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.analyzeUsageFrequency(request)));
    }

    @Operation(summary = "营销标签应用情况监控分析-标签应用影响范围")
    @PostMapping("/influence")
    public ResponseEntity<CommonResponse<java.util.List<TagUsageRecord>>> influence(@RequestBody TagRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Tag code is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.analyzeInfluenceRange(request)));
    }

    @Operation(summary = "实时监控标签应用情况和效果-标签应用触发情况")
    @PostMapping("/trigger")
    public ResponseEntity<CommonResponse<TagUsageRecord>> trigger(@RequestBody TagRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Tag code is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.monitorTrigger(request)));
    }

    @Operation(summary = "实时监控标签应用情况和效果-标签应用应用效果")
    @PostMapping("/effect")
    public ResponseEntity<CommonResponse<TagUsageRecord>> effect(@RequestBody TagRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Tag code is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.evaluateEffect(request)));
    }

    @Operation(summary = "实时监控标签应用情况和效果-标签应用用户反馈")
    @PostMapping("/feedback")
    public ResponseEntity<CommonResponse<TagUsageRecord>> feedback(@RequestBody TagRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Tag code is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.collectFeedback(request)));
    }

    private boolean invalid(TagRequest request) {
        return request == null || request.getTagCode() == null || request.getTagCode().trim().isEmpty();
    }
}
