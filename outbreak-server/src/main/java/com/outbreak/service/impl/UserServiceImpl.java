package com.outbreak.service.impl;

import com.outbreak.entity.User;
import com.outbreak.mapper.UserMapper;
import com.outbreak.service.UserService;
import com.outbreak.utils.CodecUtils;
import com.outbreak.utils.JwtTokenUtil;
import com.outbreak.utils.NumberUtils;
import com.outbreak.utils.RegularUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "user:verify:";
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";

    @Override
    public Boolean checkUser(String data, int type) {
        if (type == 1) {
            return userMapper.selectNameCount(data) == 0;
        } else if (type == 2) {
            return userMapper.selectPhoneCount(data) == 0;
        } else {
            return null;
        }
    }

    @Override
    public void verifyCode(String email) {
        if (StringUtils.isBlank(email)) {
            return;
        }
        String code = NumberUtils.generateCode(6);
        Map<String, String> msg = new HashMap<>();
        msg.put("email", email);
        msg.put("code", code);
        amqpTemplate.convertAndSend("OUTBREAK.EXCHANGE", "email.verify", msg);
        redisTemplate.opsForValue().set(KEY_PREFIX + email, code, 15, TimeUnit.MINUTES);
    }

    @Override
    public String register(User user, String code) {
        String message = "合法";
        String redisCode = redisTemplate.opsForValue().get(KEY_PREFIX + user.getEmail());
        if (!StringUtils.equals(code, redisCode) || RegularUtils.isEmail(user.getEmail())) {
            message = "不合法";
            return message;
        }
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        System.out.println(user.toString());
        user.setPassword(CodecUtils.md5Hex(user.getPassword(), salt));
        user.setCreated(new Date());
        this.userMapper.insertSelective(user);
        redisTemplate.delete(KEY_PREFIX + user.getEmail());
        return message;
    }

    @Override
    public User queryUser(String username, String password) {
        User user = userMapper.selectByName(username);
        if (user == null) {
            return user;
        }
        password = CodecUtils.md5Hex(password, user.getSalt());
        if (!StringUtils.equals(password, user.getPassword())) {
            return null;
        }
        return user;
    }

    @Override
    public String login(String username, String password) {
        User user = userMapper.selectByName(username);
        if (user == null) {
            return null;
        }
        password = CodecUtils.md5Hex(password, user.getSalt());
        if (!StringUtils.equals(password, user.getPassword())) {
            return null;
        }
        String token = jwtTokenUtil.generateToken(user);
        return token;
    }

    @Override
    public User getUserByName(String username) {
        User user = userMapper.selectByName(username);
        return user;
    }
}
