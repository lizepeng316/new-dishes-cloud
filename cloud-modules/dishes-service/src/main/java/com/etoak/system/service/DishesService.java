package com.etoak.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.common.vo.PageVO;
import com.etoak.system.entity.Dishes;
import com.etoak.system.vo.DishesVO;


import java.util.List;

/**
 * DishesService
 *
 * @author etoak
 * @since 2026-04-28
 */
public interface DishesService extends IService<Dishes> {

    /**
     * 添加菜品
     *
     * @param dishes
     */
    void add(Dishes dishes);

    /**
     * 分页查询
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @param dishes   其它查询参数
     * @return PageVO
     */
    PageVO<DishesVO> listPage(int pageNum, int pageSize, Dishes dishes);

    /**
     * 更新菜品
     *
     * @param id     菜品id
     * @param dishes 其它更新参数
     */
    void update(int id, Dishes dishes);

    /**
     * 删除菜品
     *
     * @param id 菜品id
     */
    void delete(int id);

    List<DishesVO> listByIdList(List<Integer> ids);
}
