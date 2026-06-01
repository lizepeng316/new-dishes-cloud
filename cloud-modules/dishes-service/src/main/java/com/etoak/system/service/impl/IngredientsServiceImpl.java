package com.etoak.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.common.exception.ParamException;
import com.etoak.common.vo.PageVO;
import com.etoak.system.entity.Ingredients;
import com.etoak.system.mapper.IngredientsMapper;
import com.etoak.system.service.IngredientsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsServiceImpl extends ServiceImpl<IngredientsMapper, Ingredients> implements IngredientsService {

    @Override
    public void add(Ingredients ingredients) {
        if (ObjectUtils.isNotEmpty(this.getByName(ingredients.getName()))) {
            throw new ParamException("食材名称重复！");
        }
        this.save(ingredients);
    }

    @Override
    public PageVO<Ingredients> listPage(int pageNum, int pageSize, Ingredients ingredients) {
        PageHelper.startPage(pageNum, pageSize);
        List<Ingredients> ingredientsList = this.getList(ingredients);
        return new PageVO<>(new PageInfo<>(ingredientsList).getTotal(), ingredientsList);
    }

    @Override
    public List<Ingredients> getList(Ingredients ingredients) {
        return lambdaQuery()
                .likeRight(StringUtils.isNotEmpty(ingredients.getName()), Ingredients::getName, ingredients.getName())
                .eq(StringUtils.isNotEmpty(ingredients.getType()), Ingredients::getType, ingredients.getType())
                .list();
    }

    @Override
    public void update(int id, Ingredients ingredients) {
        if (ObjectUtils.isEmpty(this.getById(id))) {
            throw new ParamException("食材不存在！");
        }

        String ingredientsName = ingredients.getName();
        if (StringUtils.isNotEmpty(ingredientsName)) {
            Ingredients savedIngredients = this.getByName(ingredientsName);
            if (ObjectUtils.isNotEmpty(savedIngredients) && !savedIngredients.getId().equals(id)) {
                throw new ParamException("不能修改为其它食材的名称！");
            }
        }

        ingredients.setId(id);
        this.updateById(ingredients);
    }

    @Override
    public void delete(int id) {
        if (ObjectUtils.isEmpty(this.getById(id))) {
            throw new ParamException("食材不存在！");
        }

        this.removeById(id);
    }

    /**
     * 根据食材名称查询食材信息
     *
     * @param name 食材名称
     * @return 食材信息
     */
    private Ingredients getByName(String name) {
        return lambdaQuery().eq(Ingredients::getName, name).one();
    }
}
