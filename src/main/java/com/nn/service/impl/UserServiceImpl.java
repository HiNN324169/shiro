package com.nn.service.impl;

import com.nn.domain.User;
import com.nn.service.UserService;

/**
 * @ClassName UserService
 * @Author nn
 * @Date 2020/5/8 11:16
 */
public class UserServiceImpl implements UserService {

    @Override
    public User queryUserById(String username) {
        User user = null;
        switch (username){
            case "admin":
                user = new User("admin","123456","男",0);
                break;
            case "zhangsan":
                user = new User("zhangsan","123456","男",1);
                break;

            case "lisi":
                user = new User("lisi","123456","男",1);
                break;

            case "xiaohua":
                user = new User("xiaohua","123456","女",1);
                break;
        }
        return user;
    }
}
