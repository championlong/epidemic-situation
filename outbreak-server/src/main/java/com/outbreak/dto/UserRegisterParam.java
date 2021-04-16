package com.outbreak.dto;

import com.outbreak.entity.User;

public class UserRegisterParam {
    private User user;
    private String verify;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

}
