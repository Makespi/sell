package com.imooc.repositoty;

import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ProductStatusEnum;
import org.apache.catalina.LifecycleState;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoTest {

    @Autowired
    private ProductInfoRepository repository;
    @Test
    public void statustest(){
        //List<ProductInfo> list = repository.findByProductStatus();
        //Assert.assertNotEquals(0,);
    }

    @Test
    public void  savetest(){
        ProductInfo productInfos = new ProductInfo();
        productInfos.setProductId("1");
        productInfos.setProductName("皮蛋粥");
        productInfos.setProductPrice(new BigDecimal(2.3));
        productInfos.setProductStock(100);
        productInfos.setProductDescription("好喝");
        productInfos.setProductIcon("http://xxxx.jpg");
        productInfos.setProductStatus(0);
        productInfos.setCategoryType(12);
        ProductInfo result = repository.save(productInfos);
        Assert.assertNotNull(result);
    }



}