package com.imooc.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class OrderForm {
   /**
    * 加了@NotEmpty的String类，Collection、Map、数组，是不能为null或者长度为0的（String、Collection、Map
    的isEmpth()方法）
    */
   /*
   买家名字
    */
   @NotEmpty(message = "买家名字不能为空")
   private String name;

   /*
    买家手机号
    */
   @NotEmpty(message = "手机号必填")
   private String phone;
   /*
     买家地址
    */
   @NotEmpty(message = "地址必填")
   private String address;
   /*
    买家微信openid
    */
   @NotEmpty(message = "openid必填")
   private String openid;
   /*
   购物车
    */
   @NotEmpty(message = "购物车不能为空")
   private String items;

}
