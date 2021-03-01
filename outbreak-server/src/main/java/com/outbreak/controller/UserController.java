package com.outbreak.controller;

import com.outbreak.common.api.CommonResult;
import com.outbreak.common.api.ResultCode;
import com.outbreak.dto.UserLoginParam;
import com.outbreak.entity.User;
import com.outbreak.service.UserService;
import com.outbreak.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/check/{data}/{type}", method = RequestMethod.GET)
    public CommonResult<Boolean> checkUser(@PathVariable("data")String data, @PathVariable("type")Integer type){
        Boolean check = userService.checkUser(data, type);
        if(check == null){
            return CommonResult.failed(ResultCode.VALIDATE_FAILED,"Bad Request");
        }
        return CommonResult.success(check);
    }

    @RequestMapping(value = "/code", method = RequestMethod.POST)
    public CommonResult verifyCode(@RequestParam("email")String email){
        this.userService.verifyCode(email);
        return CommonResult.success(1);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult register(User user, @RequestParam("code")String code){
        String message = this.userService.register(user, code);
        return CommonResult.success(1,message);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public CommonResult<User> queryUser(@RequestParam("username")String username,@RequestParam("password")String password){
        User user = userService.queryUser(username, password);
        if (user == null){
            return CommonResult.failed("没有此用户");
        }
        return CommonResult.success(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody UserLoginParam userLoginParam){
        String token = userService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public CommonResult getUserInfo(HttpServletRequest request){
        String token = request.getHeader(this.tokenHeader);
        if(token==null){
            return CommonResult.unauthorized(null);
        }
        String username = jwtTokenUtil.getUserNameFromToken(token);
        System.out.println(username);
        User user = userService.getUserByName(username);
        System.out.println(user);
        return CommonResult.success(user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public CommonResult logout() {
        return CommonResult.success(null);
    }
}
