package com.nn.service;

import com.nn.domain.User;

/**
 * @ClassName UserService
 * @Author nn
 * @Date 2020/5/8 14:36
 */
public interface UserService {

    /**
     *  根据 用户名称 查用户
     * @param username
     * @return
     */
    public User queryUserById(String username);
}
