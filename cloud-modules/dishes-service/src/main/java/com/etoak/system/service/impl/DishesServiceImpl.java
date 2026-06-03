package com.etoak.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.common.exception.ParamException;
import com.etoak.common.vo.PageVO;
import com.etoak.system.entity.Dishes;
import com.etoak.system.mapper.DishesMapper;
import com.etoak.system.service.DishesService;
import com.etoak.system.vo.DishesVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author etoak
 * @since 2026-04-28
 */
@Service
public class DishesServiceImpl extends ServiceImpl<DishesMapper, Dishes> implements DishesService {

    @Override
    public void add(Dishes dishes) {
        if (ObjectUtils.isNotEmpty(this.getByName(dishes.getName()))) {
            throw new ParamException("菜品名称重复！");
        }
        this.save(dishes);
    }

    @Override
    public PageVO<DishesVO> listPage(int pageNum, int pageSize, Dishes dishes) {
        PageHelper.startPage(pageNum, pageSize);
        List<DishesVO> dishesVOList = this.baseMapper.getList(dishes);
        return new PageVO<>(new PageInfo<>(dishesVOList).getTotal(), dishesVOList);
    }

    @Override
    public void update(int id, Dishes dishes) {
        if (ObjectUtils.isEmpty(this.getById(id))) {
            throw new ParamException("菜品不存在！");
        }

        String dishesName = dishes.getName();
        if (StringUtils.isNotEmpty(dishesName)) {
            // 如果 name 参数不为空，则根据 name 查询菜品
            Dishes savedDishes = this.getByName(dishesName);
            if (ObjectUtils.isNotEmpty(savedDishes) && !savedDishes.getId().equals(id)) {
                throw new ParamException("不能修改为其它菜品名称！");
            }
        }

        dishes.setId(id);
        this.updateById(dishes);
    }

    @Override
    public void delete(int id) {
        if (ObjectUtils.isEmpty(this.getById(id))) {
            throw new ParamException("菜品不存在！");
        }
        this.removeById(id);
    }

    @Override
    public List<DishesVO> listByIdList(List<Integer> ids) {
        return baseMapper.listByIds(ids);
    }

    /**
     * 根据菜品名称查询菜品详情
     *
     * @param name 菜品名称
     * @return Dishes
     */
    private Dishes getByName(String name) {
        return lambdaQuery().eq(Dishes::getName, name).one();
    }
}
