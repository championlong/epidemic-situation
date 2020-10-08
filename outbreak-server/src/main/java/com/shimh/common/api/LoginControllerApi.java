package com.shimh.common.api;

import com.shimh.common.annotation.LogAnnotation;
import com.shimh.common.constant.Base;
import com.shimh.common.constant.ResultCode;
import com.shimh.common.result.Result;
import com.shimh.entity.User;
import com.shimh.oauth.OAuthSessionManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 1159588554@qq.com
 * @date 2020/9/22 19:22
 */
@Api(tags = {"登录"})
public interface LoginControllerApi {
    @ApiOperation("登录")
    Result login(@RequestBody User user);

    @ApiOperation("注册")
    Result register(User user);

    @ApiOperation("handleLogin")
    Result handleLogin(HttpServletRequest request);

    @ApiOperation("退出")
    Result logout();
}
