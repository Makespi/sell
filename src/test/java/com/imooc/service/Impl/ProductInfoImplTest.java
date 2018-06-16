package com.imooc.service.Impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.service.ProductInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;




@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoImplTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void testPage(){
        PageRequest pageRequest = new PageRequest(0,2);
        Page<ProductInfo> productInfos = productInfoService.findAll(pageRequest);
        System.out.print(productInfos.getTotalElements());

    }

    @Test
    public void testSave(){
       ProductInfo productInfos = new ProductInfo();
        productInfos.setProductId("2");
        productInfos.setProductName("皮蛋粥");
        productInfos.setProductPrice(new BigDecimal(2.4));
        productInfos.setProductStock(100);
        productInfos.setProductDescription("还可以");
        productInfos.setProductIcon("http://xxxx.jpg");
        productInfos.setProductStatus(0);
        productInfos.setCategoryType(12);
        ProductInfo result = productInfoService.save(productInfos);
        Assert.assertNotNull(result);
    }

}