package com.sfes.service;

import com.github.pagehelper.PageInfo;
import com.sfes.pojo.ProductInfo;
import com.sfes.pojo.vo.ProductInfoVo;

import java.util.List;

/**
 * @author : sfes
 * @date : 2023/10/28
 */
public interface ProductInfoService {

    //显示全部商品不分页
    List<ProductInfo> getAll();

    PageInfo splitPage(int pageNum,int pageSize);

    int save(ProductInfo info);

    ProductInfo getById(int id);

    int update(ProductInfo info);

    int delete(int pid);

    int deleteBatch(String[] ids);

    List<ProductInfo> selectCondition(ProductInfoVo productInfoVo);

    //多条件查询分页
    PageInfo splitPageVo(ProductInfoVo productInfoVo,int pageSize);
}
