package com.imooc.controller;


import com.imooc.VO.ProductInfoVO;
import com.imooc.VO.ProductVO;
import com.imooc.VO.ResultVO;
import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductInfoService;
import com.imooc.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
@Slf4j
public class BuyerProductController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductInfoService productInfoService;


     @GetMapping("/list")
     public ResultVO list(){
         log.info("进入lis");
         //1.查询所有上架的商品
         List<ProductInfo> upAll= productInfoService.findUpAll();
         //2.查询类目（一次性查询）
         List<Integer> categoryTypeList = new ArrayList<>();
         //传统方法
         for (ProductInfo productInfo : upAll){
            categoryTypeList.add(productInfo.getCategoryType());
         }
         List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

         //3.数据的拼接
         List<ProductVO> productVOList= new ArrayList<>();
         for(ProductCategory productCategory : productCategoryList){
             ProductVO productVO = new ProductVO();
             productVO.setCategoryName(productCategory.getCategoryName());
             productVO.setCategoryType(productCategory.getCategoryType());

             List<ProductInfoVO> productInfoVOList = new ArrayList<>();
             for (ProductInfo productInfos : upAll){
                 if(productInfos.getCategoryType().equals(productCategory.getCategoryType())){
                     ProductInfoVO productInfoVO = new ProductInfoVO();
                     BeanUtils.copyProperties(productInfos,productInfoVO);
                     productInfoVOList.add(productInfoVO);
                 }
             }
             productVO.setProductInfoVOList(productInfoVOList);
             productVOList.add(productVO);
         }
         return ResultVOUtil.success(productVOList);
     }
}
