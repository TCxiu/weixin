package com.xiu.wserver.controller.api;

import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.GroupsApi;
import com.jfinal.weixin.sdk.api.TagApi;
import com.xiu.wserver.model.Tag;
import net.dreamlu.weixin.annotation.WxApi;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/9 10:36
 * @Description 类描述:
 */

@RestController
@WxApi
@RequestMapping("tag")
public class UserTagController {

    /**
     * @method 方法名: createTag
     * @Decription 方法描述: 添加分组
     *
     * @params 传入参数:[tag]
     * @return 返回值类型:java.lang.Object
     * @throws
     */
    @PostMapping("")
    public Object createTag(@RequestBody Tag tag){
        ApiResult result = TagApi.create(tag.getName());
        return  result.isSucceed() ? result: result.getErrorMsg();
    }

    /**
     * @method 方法名: delTag
     * @Decription 方法描述: 删除分组
     *
     * @params 传入参数:[tagId]
     * @return 返回值类型:java.lang.Object
     * @throws
     */
    @DeleteMapping("/{tagId}")
    public Object delTag(@PathVariable Integer tagId){
        ApiResult result = TagApi.delete(tagId);
        return  result.isSucceed() ? result: result.getErrorMsg();
    }

    /**
     * @method 方法名: listTag
     * @Decription 方法描述: 列出所有分组
     *
     * @params 传入参数:[]
     * @return 返回值类型:java.lang.Object
     * @throws
     */
    @GetMapping("")
    public Object listTag(){
        ApiResult result = TagApi.get();
        return  result.isSucceed() ? result: result.getErrorMsg();
    }

    /**
     * @method 方法名: updateTag
     * @Decription 方法描述: 分组重命名
     *
     * @params 传入参数:[tagId, tag]
     * @return 返回值类型:java.lang.Object
     * @throws
     */
    @PutMapping("/{tagId}")
    public  Object updateTag(@PathVariable Integer tagId ,@RequestBody Tag tag){
        ApiResult result = TagApi.update(tagId, tag.getName());
        return  result.isSucceed() ? result: result.getErrorMsg();
    }

    /**
     * @method 方法名: moveUserToTag
     * @Decription 方法描述:  移动单个用户到指定分组
     *
     * @params 传入参数:[userId, tagId]
     * @return 返回值类型:java.lang.Object
     * @throws
     */
    @GetMapping("/{userId}/{tagId}")
    public Object moveUserToTag(@PathVariable String userId,@PathVariable Integer tagId){
        ApiResult result = GroupsApi.membersUpdate(userId, tagId);
        return  result.isSucceed() ? result: result.getErrorMsg();
    }

    /**
     * @method 方法名: moveUsersToTag
     * @Decription 方法描述:
     *
     * @params 传入参数:[userIds, tagId]  批量移动用户到指定分组
     * @return 返回值类型:java.lang.Object
     * @throws
     */
    @GetMapping("/{tagId}")
    public Object moveUsersToTag(@RequestParam String[] userIds, @PathVariable Integer tagId){
        List<String> users = Arrays.asList(userIds);
        ApiResult result = TagApi.batchAddTag(tagId ,users);
        return  result.isSucceed() ? result: result.getErrorMsg();
    }

}
