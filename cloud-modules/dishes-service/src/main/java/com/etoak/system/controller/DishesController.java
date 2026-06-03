package com.etoak.system.controller;


import com.etoak.common.vo.PageVO;
import com.etoak.common.vo.ResultVO;
import com.etoak.system.entity.Dishes;
import com.etoak.system.service.DishesService;
import com.etoak.system.vo.DishesVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端控制器
 *
 * @author etoak
 * @since 2026-04-28
 */
@RestController
@RequestMapping("/dishes")
public class DishesController {

    DishesService dishesService;

    public DishesController(DishesService dishesService) {
        this.dishesService = dishesService;
    }

    /**
     * 添加菜品  post /dishes
     */
    @PostMapping
    public ResultVO<Object> add(@Valid @RequestBody Dishes dishes) {
        dishesService.add(dishes);
        return ResultVO.success();
    }

    /**
     * 分页查询  get /dishes/list
     */
    @GetMapping("/list")
    public ResultVO<PageVO<DishesVO>> list(
            @RequestParam(required = false, defaultValue = "1") int pageNum,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            Dishes dishes) {
        PageVO<DishesVO> pageVO = dishesService.listPage(pageNum, pageSize, dishes);
        return ResultVO.success(pageVO);
    }

    /**
     * 更新菜品 post /dishes/{id}
     */
    @PostMapping("/{id}")
    public ResultVO<Object> update(@PathVariable int id, @RequestBody Dishes dishes) {
        dishesService.update(id, dishes);
        return ResultVO.success();
    }

    /**
     * 删除菜品 post /dishes/delete?id=1
     */
    @PostMapping("/delete")
    public ResultVO<Object> delete(int id) {
        dishesService.delete(id);
        return ResultVO.success();
    }

    @GetMapping("/{ids}")
    public ResultVO<List<DishesVO>> getByIds(@PathVariable List<Integer> ids) {
        List<DishesVO> dishesVOList = dishesService.listByIdList(ids);
        return ResultVO.success(dishesVOList);
    }
}