package com.modoop.zerg.naga.service.dao;

import com.modoop.zerg.orochi.entity.Product;
import com.modoop.zerg.orochi.exception.EntityNotFoundException;

public interface ProductDao
{
    /**
     * 通过商品ID查找商品
     *
     * @param id
     * @return
     */
    abstract public Product findProduct(Long id) throws EntityNotFoundException;
}
