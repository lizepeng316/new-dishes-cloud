package com.etoak.system.vo;

import com.etoak.system.entity.Dishes;
import lombok.Data;

@Data
public class DishesVO extends Dishes {
    /**
     * 主要食材名称
     */
    private String mainName;

    /**
     * 辅助食材名称
     */
    private String minorName;

    /**
     * 调料名称
     */
    private String seasoningName;
}