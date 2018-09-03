package com.ant.webPage.controller;

import com.ant.admin.common.annotation.SysLog;
import com.ant.admin.common.shiro.ShiroUtils;
import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.admin.common.validator.Assert;
import com.ant.admin.common.validator.ValidatorUtils;
import com.ant.admin.common.validator.group.AddGroup;
import com.ant.admin.common.validator.group.UpdateGroup;
import com.ant.admin.controller.AbstractController;
import com.ant.admin.service.UserRoleService;
import com.ant.admin.service.UserService;
import com.ant.entity.User;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/15 11:59
 */
@Controller
@RequestMapping("/user")
public class UserController extends  AbstractController{

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 所有用户列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:user:list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return Result.ok().put("page", page);
    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public Result info(){
        return Result.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @RequestMapping("/password")
    public Result password(String password, String newPassword){
        Assert.isBlank(newPassword, "新密码不为能空");

        //原密码
        password = ShiroUtils.sha256(password, getUser().getSalt());
        //新密码
        newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt());

        //更新密码
        boolean flag = userService.updatePassword(getUserId(), password, newPassword);
        if(!flag){
            return Result.error("原密码不正确");
        }

        return Result.ok();
    }

    /**
     * 用户信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public Result info(@PathVariable("userId") Integer userId){
        User user = userService.selectById(userId);

        //获取用户所属的角色列表
        List<Integer> roleIdList = userRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return Result.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @RequestMapping("/save")
    @RequiresPermissions("sys:user:save")
    public Result save(@RequestBody User user){
        ValidatorUtils.validateEntity(user, AddGroup.class);

        userService.save(user);

        return Result.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    public Result update(@RequestBody User user){
        ValidatorUtils.validateEntity(user, UpdateGroup.class);

        userService.update(user);

        return Result.ok();
    }

    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public Result delete(@RequestBody Integer[] userIds){
        if(ArrayUtils.contains(userIds, 1)){
            return Result.error("系统管理员不能删除");
        }

        if(ArrayUtils.contains(userIds, getUserId())){
            return Result.error("当前用户不能删除");
        }

        userService.deleteBatchIds(Arrays.asList(userIds));

        return Result.ok();
    }
}
