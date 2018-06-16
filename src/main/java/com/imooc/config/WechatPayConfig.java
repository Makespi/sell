package com.imooc.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WechatPayConfig {

        @Autowired
        private WechatAccountConfig accountConfig;



}
