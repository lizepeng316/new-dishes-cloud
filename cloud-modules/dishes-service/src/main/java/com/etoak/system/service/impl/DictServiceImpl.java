package com.etoak.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.system.entity.Dict;
import com.etoak.system.mapper.DictMapper;
import com.etoak.system.service.DictService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DictService 服务实现
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public List<Dict> getList(String type) {
        return lambdaQuery()
                .eq(Dict::getType, type)
                .orderByAsc(Dict::getSort)
                .list();
    }
}