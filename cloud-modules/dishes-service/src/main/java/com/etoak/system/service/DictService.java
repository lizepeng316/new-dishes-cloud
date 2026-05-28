package com.etoak.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.system.entity.Dict;

import java.util.List;

/**
 * DictService
 */
public interface DictService extends IService<Dict> {

    /**
     * 查询字典列表
     *
     * @param type 字典类型
     * @return 字典列表
     */
    List<Dict> getList(String type);
}