package com.xiu.wserver.controller.api;

import com.alibaba.fastjson.JSON;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.MenuApi;
import com.xiu.wserver.model.Menu;
import net.dreamlu.weixin.annotation.WxApi;
import org.springframework.web.bind.annotation.*;


@RestController
@WxApi
@RequestMapping("menu")
public class MenuController {


    /**
     * @method 方法名: addMenu
     * @Decription 方法描述: 创建菜单
     *
     * @params 传入参数:[menu]
     * @return 返回值类型:java.lang.Object
     * @throws
     */
    @PostMapping("")
    public Object addMenu(@RequestBody Menu menu){

        String menuJson = JSON.toJSONString(menu);

        ApiResult result = MenuApi.createMenu(menuJson);

        return  result.isSucceed() ? result : result.getErrorMsg();
    }


    /**
     * @method 方法名: delMenu
     * @Decription 方法描述: 删除菜单
     *
     * @params 传入参数:[]
     * @return 返回值类型:java.lang.Object
     * @throws
     */
    @DeleteMapping("")
    public Object delMenu(){

        ApiResult result = MenuApi.deleteMenu();

        return  result.isSucceed() ? result : result.getErrorMsg();
    }




    /**
     * @method 方法名: menuList
     * @Decription 方法描述: 获取菜单列表
     *
     * @params 传入参数:[]
     * @return 返回值类型:java.lang.Object
     * @throws
     */
    @GetMapping("")
    public Object menuList() {
        ApiResult apiResult = MenuApi.getMenu();
        return apiResult;
    }
}
