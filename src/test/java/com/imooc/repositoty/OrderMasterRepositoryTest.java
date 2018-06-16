package com.imooc.repositoty;

import com.imooc.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;
    private final String OPENID="101010";
    @Test
    public void testsave(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234562131");
        orderMaster.setBuyerName("周大哥");
        orderMaster.setBuyerAddress("长河区4号44");
        orderMaster.setBuyerPhone("10909");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(55));
        OrderMaster result= repository.save(orderMaster);
        Assert.assertNotNull(result);
    }
       @Test
       public void findByBuyerOpenid() throws  Exception{
           PageRequest request = new PageRequest(0,1);
           Page<OrderMaster> orderMasters = repository.findByBuyerOpenid(OPENID,request);
           Assert.assertNotEquals(0,orderMasters.getTotalElements());
       }

}