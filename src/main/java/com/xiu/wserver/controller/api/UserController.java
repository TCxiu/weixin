package com.xiu.wserver.controller.api;

import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.GroupsApi;
import com.jfinal.weixin.sdk.api.UserApi;
import net.dreamlu.weixin.annotation.WxApi;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/9 09:36
 * @Description 类描述:
 */

@RestController
@WxApi
@RequestMapping("user")
public class UserController {

    /**
     * @method 方法名: usersInfo
     * @Decription 方法描述: 根据用户群id获取每个用户信息
     *
     * @params 传入参数:[ids]
     * @return 返回值类型:java.lang.Object
     * @throws
     */
    @GetMapping("/info")
    public Object usersInfo(@RequestParam ArrayList<String> ids){

        ApiResult result = UserApi.batchGetUserInfo(ids);

        return  result.isSucceed() ? result : result.getErrorMsg();
    }

    /**
     * @method 方法名: userList
     * @Decription 方法描述: 获取所有用户信息
     *
     * @params 传入参数:[]
     * @return 返回值类型:java.lang.Object
     * @throws
     */
    @GetMapping("")
    public Object userList() {
        ApiResult users = UserApi.getFollows();
        Map data = users.getMap("data");
        ArrayList<String> openids = (ArrayList) data.get("openid");

        ArrayList<ApiResult> result = new ArrayList();
        for (int i = 0; i < openids.size(); i++) {
            ApiResult userInfos = UserApi.getUserInfo(openids.get(i));
            result.add(userInfos);
        }

        return ObjectUtils.isEmpty(result) ? "" : result;
    }

    /**
     * @method 方法名: userOne
     * @Decription 方法描述:  获取指定用户信息
     *
     * @params 传入参数:[userId]
     * @return 返回值类型:java.lang.Object
     * @throws
     */
    @GetMapping("/{userId}")
    public Object userOne(@PathVariable String userId) {
        ApiResult result = UserApi.getUserInfo(userId);
        return  result.isSucceed() ? result : result.getErrorMsg();
    }

    /**
     * @method 方法名: userTag
     * @Decription 方法描述: 获取用户所在群组
     *
     * @params 传入参数:[userId]
     * @return 返回值类型:java.lang.Object
     * @throws
     */
    @GetMapping("/{userId}/tag")
    public Object userTag(@PathVariable String userId){
        ApiResult result = GroupsApi.getId(userId);
        return  result.isSucceed() ? result : result.getErrorMsg();
    }

    @PutMapping("/{userId}/remark")
    public Object editUserRemark(@PathVariable String userId,@RequestParam String remark){
        ApiResult result = UserApi.updateRemark(userId, remark);
        return  result.isSucceed() ? result : result.getErrorMsg();
    }
}
