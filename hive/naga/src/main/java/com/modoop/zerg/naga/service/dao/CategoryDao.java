package com.modoop.zerg.naga.service.dao;

import com.modoop.zerg.orochi.entity.Category;
import com.modoop.zerg.orochi.exception.EntityNotFoundException;

import java.util.List;

public interface CategoryDao
{
    /**
     * 查找分类
     *
     * @param id
     * @return Category
     * @throws EntityNotFoundException
     */
    abstract public Category findCategory(Long id) throws EntityNotFoundException;

    /**
     * 读取所有分类
     *
     * @return List
     */
    abstract public List<Category> findCategories();
}
