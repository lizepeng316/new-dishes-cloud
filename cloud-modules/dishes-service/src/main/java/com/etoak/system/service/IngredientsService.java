package com.etoak.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.common.vo.PageVO;
import com.etoak.system.entity.Ingredients;


import java.util.List;

public interface IngredientsService extends IService<Ingredients> {

    /**
     * 添加食材
     *
     * @param ingredients 食材参数
     */
    void add(Ingredients ingredients);

    /**
     * 分页查询
     *
     * @param pageNum     页码
     * @param pageSize    每页记录数
     * @param ingredients 查询参数
     * @return PageVO
     */
    PageVO<Ingredients> listPage(int pageNum, int pageSize, Ingredients ingredients);

    /**
     * 查询食材列表
     *
     * @param ingredients 查询参数
     * @return List<Ingredients>
     */
    List<Ingredients> getList(Ingredients ingredients);

    /**
     * 更新食材
     *
     * @param id          食材 id
     * @param ingredients 更新参数
     */
    void update(int id, Ingredients ingredients);

    /**
     * 删除食材
     *
     * @param id 食材id
     */
    void delete(int id);
}
