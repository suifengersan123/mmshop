package com.sfes.service.impl;

import com.sfes.mapper.ProductTypeMapper;
import com.sfes.pojo.ProductType;
import com.sfes.pojo.ProductTypeExample;
import com.sfes.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : sfes
 * @date : 2023/11/2
 */
@Service("ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;
    @Override
    public List<ProductType> getAll() {
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
