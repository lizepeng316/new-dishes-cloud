package com.etoak.system.dto;

import com.etoak.system.entity.OrderItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    @NotBlank(message = "preparingTime 必填")
    private String prepareTime;

    @NotEmpty(message = "itemList 必填")
    @Valid
    private List<OrderItem> itemList;
}
