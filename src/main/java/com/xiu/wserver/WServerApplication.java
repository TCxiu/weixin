package com.xiu.wserver;

import net.dreamlu.weixin.annotation.EnableDreamWeixin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableDreamWeixin
@EnableCaching
public class WServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WServerApplication.class, args);
    }
}
