package com.etoak.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 菜品表对应的实体类
 */
@Data
@TableName("t_dishes")
public class Dishes {

    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 菜品名称
     */
    @NotBlank(message = "name 必填")
    private String name;

    /**
     * 菜品图片
     */
    @NotBlank(message = "url 必填")
    private String url;

    /**
     * 主要食材ID
     */
    @NotNull(message = "main 必填")
    private Integer main;

    /**
     * 主要食材份量, 单位g
     */
    @NotNull(message = "mainNum 必填")
    private Integer mainNum;

    /**
     * 辅助食材ID
     */
    @NotNull(message = "minor 必填")
    private Integer minor;

    /**
     * 辅助食材份量
     */
    @NotNull(message = "minorNum 必填")
    private Integer minorNum;

    /**
     * 调料ID
     */
    @NotNull(message = "seasoning 必填")
    private Integer seasoning;

    /**
     * 调料份量
     */
    @NotNull(message = "seasoningNum 必填")
    private Integer seasoningNum;

    /**
     * 菜品介绍
     */
    @NotBlank(message = "remark 必填")
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
