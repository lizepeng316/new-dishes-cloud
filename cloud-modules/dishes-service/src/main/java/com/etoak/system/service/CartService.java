package com.etoak.system.service;

import com.etoak.system.entity.Cart;

import java.util.List;

public interface CartService {
    void saveOrUpdate(Cart cart);

    List<Cart> list();

    void delete(List<String> ids);
}
