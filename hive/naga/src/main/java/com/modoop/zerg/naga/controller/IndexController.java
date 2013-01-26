package com.modoop.zerg.naga.controller;

import com.modoop.zerg.naga.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController
{
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService service)
    {
        this.productService = service;
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String onIndex()
    {
        return "index";
    }
}
