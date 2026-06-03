package com.etoak.system.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Cart {
    @NotNull(message = "菜品id不能为空")
    private Integer dishesId;

    private String dishesName;
    @NotNull(message = "菜品数量不能为空")
    private int count;
}
