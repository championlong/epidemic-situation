package com.outbreak.service;

import com.outbreak.entity.User;


public interface UserService {
    Boolean checkUser(String data, int type);

    void verifyCode(String email);

    String register(User user, String code);

    User queryUser(String username, String password);

    String login(String username, String password);

    User getUserByName(String username);
}
