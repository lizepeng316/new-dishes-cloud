package com.etoak.system.controller;


import com.etoak.common.vo.PageVO;
import com.etoak.common.vo.ResultVO;
import com.etoak.system.entity.Ingredients;
import com.etoak.system.service.IngredientsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

    IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    /**
     * 添加食材  post /ingredients
     */
    @PostMapping
    public ResultVO<Object> add(@Valid @RequestBody Ingredients ingredients) {
        ingredientsService.add(ingredients);
        return ResultVO.success();
    }

    /**
     * 分页查询 get /ingredients/list
     */
    @GetMapping("/list")
    public ResultVO<PageVO<Ingredients>> list(
            @RequestParam(required = false, defaultValue = "1") int pageNum,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            Ingredients ingredients) {
        PageVO<Ingredients> pageVO = ingredientsService.listPage(pageNum, pageSize, ingredients);
        return ResultVO.success(pageVO);
    }

    /**
     * 更新食材  post /ingredients/{id}
     */
    @PostMapping("/{id}")
    public ResultVO<Object> update(@PathVariable int id, @RequestBody Ingredients ingredients) {
        ingredientsService.update(id, ingredients);
        return ResultVO.success();
    }

    /**
     * 删除食材  post /ingredients/delete?id=1
     */
    @PostMapping("/delete")
    public ResultVO<Object> delete(int id) {
        ingredientsService.delete(id);
        return ResultVO.success();
    }

    /**
     * 根据食材类型查询食材列表
     */
    @GetMapping("/getByType")
    public ResultVO<List<Ingredients>> getByType(@RequestParam String type) {
        Ingredients ingredients = new Ingredients();
        ingredients.setType(type);

        List<Ingredients> ingredientsList = ingredientsService.getList(ingredients);
        return ResultVO.success(ingredientsList);
    }
}
