package com.etoak.system.service.impl;

import cn.hutool.core.map.MapUtil;

import com.etoak.common.constant.CommonConstant;
import com.etoak.common.redis.RedisService;
import com.etoak.common.web.context.LoginUserContext;

import com.etoak.system.entity.Cart;
import com.etoak.system.entity.Dishes;
import com.etoak.system.service.CartService;
import com.etoak.system.service.DishesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    RedisService redisService;
    DishesService dishesService;
    public CartServiceImpl(RedisService redisService, DishesService dishesService) {
        this.redisService = redisService;
        this.dishesService = dishesService;
    }

    @Override
    public void saveOrUpdate(Cart cart) {
        redisService.hset(getCartKey(),
                cart.getDishesId().toString(),
                String.valueOf(cart.getCount()));
    }

    @Override
    public List<Cart> list() {
        List<Cart> cartList = new ArrayList<>();
        Map<String, String> cartMap = redisService.hgetall(getCartKey());
        if (MapUtil.isNotEmpty(cartMap)){
            List<Integer> dishesIds = cartMap.keySet().stream()
                    .map(Integer::parseInt).toList();
            List<Dishes> dishesList = dishesService.listByIds(dishesIds);
            cartList = dishesList.stream().map(dishes -> {
                Cart cart = new Cart();
                cart.setDishesId(dishes.getId());
                cart.setDishesName(dishes.getName());
                cart.setCount(Integer.parseInt(cartMap.get(dishes.getId().toString())));
                return cart;
            }).toList();
        }
        return cartList;
    }

    @Override
    public void delete(List<String> ids) {
        redisService.hdel(getCartKey(), ids.toArray(new String[]{}));
    }

    private String getCartKey() {
        return CommonConstant.REDIS_CART_PREFIX + LoginUserContext.getUserId();
    }
}
