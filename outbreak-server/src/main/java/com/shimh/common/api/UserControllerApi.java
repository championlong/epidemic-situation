package com.shimh.common.api;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.shimh.common.annotation.LogAnnotation;
import com.shimh.common.constant.Base;
import com.shimh.common.constant.ResultCode;
import com.shimh.common.result.Result;
import com.shimh.common.util.UserUtils;
import com.shimh.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 1159588554@qq.com
 * @date 2020/9/22 19:23
 */
@Api(tags = {"用户api"})
public interface UserControllerApi {
    @ApiOperation("获取所有用户")
    Result listUsers();

    @ApiOperation("根据id获取用户")
    Result getUserById(Long id);

    @ApiOperation("获取当前登录用户")
    Result getCurrentUser(HttpServletRequest request);

    @ApiOperation("添加用户")
    Result saveUser(User user);

    @ApiOperation("修改用户")
    public Result updateUser(@RequestBody User user);

    @ApiOperation("删除用户")
    Result deleteUserById(Long id);
}
