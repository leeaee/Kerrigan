package com.modoop.zerg.naga.service;

import com.modoop.zerg.naga.service.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService
{
    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao)
    {
        this.productDao = productDao;
    }
}

