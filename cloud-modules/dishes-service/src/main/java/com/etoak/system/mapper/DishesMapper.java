package com.etoak.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.system.entity.Dishes;
import com.etoak.system.vo.DishesVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DishesMapper 接口
 *
 * @author etoak
 * @since 2026-04-28
 */
public interface DishesMapper extends BaseMapper<Dishes> {

    /**
     * 查询菜品列表
     *
     * @param dishes 查询参数
     * @return List<DishesVO>
     */
    List<DishesVO> getList(Dishes dishes);

    /**
     * 根据ids查询菜品列表
     *
     * @param ids ids
     * @return List<DishesVO>
     */
    List<DishesVO> listByIds(@Param("ids") List<Integer> ids);
}
