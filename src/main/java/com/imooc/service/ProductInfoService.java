package com.imooc.service;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoService  {

     ProductInfo findOne(String productId);
     List<ProductInfo> findUpAll();
     Page<ProductInfo> findAll(Pageable pageable);
     ProductInfo save(ProductInfo productInfo);
     //加库存
     void increaseStock(List<CartDTO> cartDTO);
     //减库存
     void decreaseStock(List<CartDTO> cartDTO);
}

