package com.imooc.converter;

import com.imooc.dataobject.OrderMaster;
import com.imooc.dto.OrderDTO;
import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class OrderMaster2OrderDTOConverter {
    public static OrderDTO convert(OrderMaster orderMaster){
       OrderDTO orderDTO = new OrderDTO();
       BeanUtils.copyProperties(orderMaster,orderDTO);
       return orderDTO;
    }
    public static List<OrderDTO> convert(List<OrderMaster> orderMasters){
        return orderMasters.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
