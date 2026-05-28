package com.etoak.system.controller;

import com.etoak.common.vo.ResultVO;
import com.etoak.system.entity.Dict;
import com.etoak.system.service.DictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * DictController - 字典接口
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    DictService dictService;

    public DictController(DictService dictService) {
        this.dictService = dictService;
    }

    /**
     * 查询字典列表: GET /dicts
     */
    @GetMapping("/list")
    public ResultVO<List<Dict>> list(@RequestParam String type) {
        List<Dict> dictList = dictService.getList(type);
        return ResultVO.success(dictList);
    }
}