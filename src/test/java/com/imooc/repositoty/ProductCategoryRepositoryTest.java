package com.imooc.repositoty;

import com.imooc.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {


    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOnetest() {
      ProductCategory productCategory= repository.findOne(1);
      System.out.print(productCategory.toString());
    }

    @Test
    public void testsave(){
       ProductCategory productCategory = new ProductCategory("wangzhe",15);
       ProductCategory result=repository.save(productCategory);
        Assert.assertNotNull(result);
    }
    @Test
    public void testCategoryTyep(){
        List<Integer> list = Arrays.asList(12,112);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());

    }
}