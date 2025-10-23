// PlayerController.java
package com.lvwyh.controller;

import com.lvwyh.dto.player.PlayerCreateReq;
import com.lvwyh.service.PlayerService;
import com.lvwyh.vo.PageResp;
import com.lvwyh.vo.player.PlayerVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/players")
@Validated
@Tag(name = "玩家管理", description = "玩家的增删改查接口")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Operation(summary = "分页查询玩家")
    @GetMapping
    public PageResp<PlayerVO> list(@RequestParam(defaultValue="1") @Min(1) int page,
                                   @RequestParam(defaultValue="10") @Min(1) int size){
        return playerService.page(page, size);
    }

    @Operation(summary = "查询所有玩家（用于房间创建勾选）")
    @GetMapping("/all")
    public List<PlayerVO> all(){
        return playerService.all();
    }

    @Operation(summary = "创建玩家")
    @PostMapping
    public PlayerVO create(@RequestBody @Validated PlayerCreateReq req){
        return playerService.create(req.getPlayerName());
    }

    @Operation(summary = "修改玩家名称")
    @PutMapping("/{playerId}")
    public boolean update(@PathVariable String playerId, @RequestBody java.util.Map<String, String> body){
        String name = body.get("playerName");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("玩家名称不能为空");
        return playerService.updateName(playerId, name.trim());
    }

    @Operation(summary = "删除玩家（会清除房间关联）")
    @DeleteMapping("/{playerId}")
    public boolean delete(@PathVariable String playerId){
        return playerService.delete(playerId);
    }
}
