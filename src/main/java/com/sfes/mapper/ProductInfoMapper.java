package com.sfes.mapper;

import com.sfes.pojo.ProductInfo;
import com.sfes.pojo.ProductInfoExample;
import java.util.List;

import com.sfes.pojo.vo.ProductInfoVo;
import org.apache.ibatis.annotations.Param;

public interface ProductInfoMapper {
    int countByExample(ProductInfoExample example);

    int deleteByExample(ProductInfoExample example);

    int deleteByPrimaryKey(Integer pId);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    List<ProductInfo> selectByExample(ProductInfoExample example);

    ProductInfo selectByPrimaryKey(Integer pId);

    int updateByExampleSelective(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    int updateByExample(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);

    //批量删除商品
    int deleteBatch(String[] ids);

    List<ProductInfo> selectCondition(ProductInfoVo vo);
}