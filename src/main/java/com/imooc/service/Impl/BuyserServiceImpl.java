package com.imooc.service.Impl;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyserServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;


    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
      return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
          OrderDTO orderDTO = checkOrderOwner(openid,orderId);
          if (orderDTO==null){
           log.error("【取消订单】查不到该订单，orderId={}",orderId);
           throw new SellException(ResultEnum.ORDER_NOT_EXIST);
          }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid,String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            return null;
        }
        /*
         不考虑大小写。如果两个字符串的长度相等，并且两个字符串中的相应字符都相等
        （忽略大小写），则认为这两个字符串是相等的。
         */
        //判断是否是自己的订单
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(orderId)) {
            log.error("【查询订单】订单与openid不一致，openid={},orderId={}", openid, orderId);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
