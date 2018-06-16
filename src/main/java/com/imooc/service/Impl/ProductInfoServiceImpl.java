package com.imooc.service.Impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repositoty.ProductInfoRepository;
import com.imooc.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
      for (CartDTO cartDTOs : cartDTOList){
         ProductInfo productInfo = repository.findOne(cartDTOs.getProductId());
         if (productInfo == null){
             throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
         }
       Integer result = productInfo.getProductStock()-cartDTOs.getProductQuantity();
         if (result < 0){
            throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
         }
         productInfo.setProductStock(result);
         repository.save(productInfo);
      }

    }
}
