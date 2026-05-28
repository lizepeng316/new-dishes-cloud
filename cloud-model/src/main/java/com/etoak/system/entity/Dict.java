package com.etoak.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 字典实体类
 */
@Data
@TableName("t_dict")
public class Dict {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 字典名称
     */
    private String label;

    /**
     * 字典值
     */
    private String value;

    /**
     * 字典排序字段
     */
    private Integer sort;
}