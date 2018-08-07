package com.imooc.repositoty;

import com.imooc.dataobject.SellerInfo;
import com.imooc.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerInfoRepositoryTest {
    @Autowired
    private SellerInfoRepository infoRepository;

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setOpenid("abc");
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        SellerInfo result = infoRepository.save(sellerInfo);
        Assert.assertNotEquals(null, result);

    }

    @Test
    public void findBy() {
        SellerInfo sellerInfo = infoRepository.findByOpenid("abc");
        Assert.assertEquals("abc",sellerInfo.getOpenid());

    }
}