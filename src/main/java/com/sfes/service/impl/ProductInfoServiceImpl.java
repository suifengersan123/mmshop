package com.sfes.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfes.mapper.ProductInfoMapper;
import com.sfes.pojo.ProductInfo;
import com.sfes.pojo.ProductInfoExample;
import com.sfes.pojo.vo.ProductInfoVo;
import com.sfes.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : sfes
 * @date : 2023/10/28
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Override
    public List<ProductInfo> getAll() {
        ProductInfoExample productInfoExample = new ProductInfoExample();
        return productInfoMapper.selectByExample(productInfoExample);
    }

    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {
        //分页插件PageHelper完成分页设置
        ProductInfoExample productInfoExample=new ProductInfoExample();
        //设置排序，按主键降序排序 新插入的在最后一行显示在第一行
        productInfoExample.setOrderByClause("p_id desc");
        PageHelper.startPage(pageNum,pageSize);
        List<ProductInfo> productInfos = productInfoMapper.selectByExample(productInfoExample);

        PageInfo<ProductInfo> pageInfo=new PageInfo<ProductInfo>(productInfos);
        return pageInfo;

    }

    @Override
    public int save(ProductInfo info) {
        return productInfoMapper.insert(info);
    }

    @Override
    public ProductInfo getById(int id) {

        return productInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(ProductInfo info) {
        return productInfoMapper.updateByPrimaryKey(info);
    }

    @Override
    public int delete(int pid) {

        return productInfoMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return productInfoMapper.deleteBatch(ids);
    }

    @Override
    public List<ProductInfo> selectCondition(ProductInfoVo productInfoVo) {
        return productInfoMapper.selectCondition(productInfoVo);
    }

    @Override
    public PageInfo splitPageVo(ProductInfoVo productInfoVo, int pageSize) {
        PageHelper.startPage(productInfoVo.getPage(),pageSize);
        List<ProductInfo> list = productInfoMapper.selectCondition(productInfoVo);
        return new PageInfo(list);
    }
}
