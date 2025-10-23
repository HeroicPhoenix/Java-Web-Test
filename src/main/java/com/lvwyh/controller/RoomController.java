// RoomController.java
package com.lvwyh.controller;

import com.lvwyh.dto.room.RoomCreateReq;
import com.lvwyh.service.RoomService;
import com.lvwyh.vo.PageResp;
import com.lvwyh.vo.room.RoomVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rooms")
@Validated
@Tag(name = "房间管理", description = "房间创建、查询及成员管理")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Operation(summary = "分页查询房间")
    @GetMapping
    public PageResp<RoomVO> list(@RequestParam(defaultValue="1") @Min(1) int page,
                                 @RequestParam(defaultValue="10") @Min(1) int size){
        return roomService.page(page, size);
    }

    @Operation(summary = "创建房间")
    @PostMapping
    public RoomVO create(@RequestBody @Validated RoomCreateReq req){
        return roomService.create(req.getRoomName(), req.getMapSizeX(), req.getMapSizeY(), req.getJoinPlayerIds());
    }

    @Operation(summary = "查询房间内成员")
    @GetMapping("/{roomId}/players")
    public List<Map<String, Object>> players(@PathVariable String roomId) {
        return roomService.listPlayers(roomId);
    }

    @Operation(summary = "批量加入成员到房间")
    @PostMapping("/{roomId}/players")
    public boolean addPlayers(@PathVariable String roomId, @RequestBody Map<String, List<String>> body) {
        List<String> ids = body.get("playerIds");
        return roomService.addPlayers(roomId, ids);
    }

    @Operation(summary = "将成员从房间移除")
    @DeleteMapping("/{roomId}/players/{playerId}")
    public boolean remove(@PathVariable String roomId, @PathVariable String playerId) {
        return roomService.removePlayer(roomId, playerId);
    }
}
