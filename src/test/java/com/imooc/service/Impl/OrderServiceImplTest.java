package com.imooc.service.Impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    private final String BUYER_OPENID="101010";
    private final String OPENNID="1530860028752597716";

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() {
      OrderDTO orderDTO = new OrderDTO();
      orderDTO.setBuyerName("慕课网");
      orderDTO.setBuyerAddress("长河街道");
      orderDTO.setBuyerOpenid(BUYER_OPENID);
      orderDTO.setBuyerPhone("112222");

      //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
       OrderDetail orderDetail = new OrderDetail();
       orderDetail.setProductId("1");
       orderDetail.setProductQuantity(2);
       orderDetailList.add(orderDetail);
       orderDTO.setOrderDetailList(orderDetailList);
       OrderDTO result = orderService.create(orderDTO);
       log.info("【创建订单】result={}",result);
    }

    @Test
    public void findOne() {
      OrderDTO result = orderService.findOne(OPENNID);
      log.info("【查询单个订单】result={}",result);
      Assert.assertEquals(OPENNID,result.getOrderId());
    }

    @Test
    public void findList() {
     PageRequest request = new PageRequest(0,3);
     Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID,request);
     Assert.assertNotEquals(0,orderDTOPage.getTotalElements());

    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(OPENNID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());

    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(OPENNID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(result.getOrderStatus(),OrderStatusEnum.FINISHED.getCode());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(OPENNID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(result.getPayStatus(), PayStatusEnum.SUCCESS.getCode());

    }
    @Test
    public void list(){

        //orderService.findList();
    }
}