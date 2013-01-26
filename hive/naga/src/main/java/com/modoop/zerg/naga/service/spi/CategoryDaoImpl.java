package com.modoop.zerg.naga.service.spi;

import com.modoop.zerg.naga.service.dao.CategoryDao;
import com.modoop.zerg.orochi.entity.Category;
import com.modoop.zerg.orochi.exception.EntityNotFoundException;
import com.modoop.zerg.taipan.core.mybatis.MyBatisDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "categoryDao")
public class CategoryDaoImpl extends MyBatisDao implements CategoryDao
{
    @Override
    public Category findCategory(Long id) throws EntityNotFoundException
    {
        Category category = getSqlSession().selectOne("categoryMapper.find", id);
        if (category == null)
        {
            throw new EntityNotFoundException("Category was not found");
        }

        return category;
    }

    @Override
    public List<Category> findCategories()
    {
        return null;
    }
}
