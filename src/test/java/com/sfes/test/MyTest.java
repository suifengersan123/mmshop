package com.sfes.test;

import com.sfes.mapper.ProductInfoMapper;
import com.sfes.pojo.ProductInfo;
import com.sfes.pojo.vo.ProductInfoVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author : sfes
 * @date : 2023/11/2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MyTest {

    @Autowired
    ProductInfoMapper productInfoMapper;
    @Test
    public void testSelectCondition(){
        ProductInfoVo productInfoVo=new ProductInfoVo();
        productInfoVo.setPname("红");
//        productInfoVo.setTypeid(1);
//        productInfoVo.setLprice(1000);
        productInfoVo.setHprice(1000);
        List<ProductInfo> productInfos = productInfoMapper.selectCondition(productInfoVo);
        for (ProductInfo productInfo : productInfos) {
            System.out.println(productInfo);
        }

    }
}
