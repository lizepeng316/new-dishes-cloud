package com.etoak.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@TableName("t_ingredients")
public class Ingredients {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 食材名称
     */
    @NotBlank(message = "name 不能为空")
    private String name;

    /**
     * 食材类型
     */
    @NotBlank(message = "type 不能为空")
    @DecimalMin(value = "1", message = "type 不能小于1")
    @DecimalMax(value = "3", message = "type 最大值为3")
    private String type;

    /**
     * 食材图片
     */
    @NotBlank(message = "url 不能为空")
    private String url;

    /**
     * 食材介绍
     */
    @NotBlank(message = "remark 不能为空")
    private String remark;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updateTime;
}