package com.etoak.system.controller;


import com.etoak.common.vo.ResultVO;
import com.etoak.system.entity.Cart;
import com.etoak.system.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping
    public ResultVO<Object> saveOrUpdate(@Valid @RequestBody Cart cart) {
        cartService.saveOrUpdate(cart);
        return ResultVO.success();
    }

    @GetMapping
    public ResultVO<List<Cart>> list() {
        return ResultVO.success(cartService.list());
    }

    @PostMapping("/{ids}")
    public ResultVO<Object> delete(@PathVariable List<String> ids) {
        cartService.delete(ids);
        return ResultVO.success();
    }
}
