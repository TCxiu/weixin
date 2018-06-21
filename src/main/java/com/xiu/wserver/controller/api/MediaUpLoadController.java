package com.xiu.wserver.controller.api;

import net.dreamlu.weixin.annotation.WxApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/9 15:04
 * @Description 类描述:
 */

@RestController
@WxApi
@RequestMapping("media")
public class MediaUpLoadController {

    @PostMapping("")
    public Object add(){

        return null;
    }

}
