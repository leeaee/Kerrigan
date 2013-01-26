package com.modoop.zerg.naga.service.spi;

import com.modoop.zerg.naga.service.dao.ProductDao;
import com.modoop.zerg.orochi.entity.Product;
import com.modoop.zerg.orochi.exception.EntityNotFoundException;
import com.modoop.zerg.taipan.core.mybatis.MyBatisDao;
import org.springframework.stereotype.Repository;

@Repository(value = "productDao")
public class ProductDaoImpl extends MyBatisDao implements ProductDao
{
    @Override
    public Product findProduct(Long id) throws EntityNotFoundException
    {
        Product product = getSqlSession().selectOne("productMapper.find", id);
        if (product == null)
        {
            throw new EntityNotFoundException("Product", id);
        }
        return product;
    }
}
