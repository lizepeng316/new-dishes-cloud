package com.etoak.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * OrderItem
 *
 * @author etoak
 * @since 2026-04-30
 */
@Data
@TableName("t_order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 菜品id
     */
    @NotNull(message = "dishesId 必填")
    private Integer dishesId;

    /**
     * 菜品数量
     */
    @NotNull(message = "dishesNum 必填")
    private Integer dishesNum;
}
