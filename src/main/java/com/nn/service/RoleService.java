package com.nn.service;

import java.util.List;

/**
 * @ClassName RoleService
 * @Author nn
 * @Date 2020/5/8 15:06
 */
public interface RoleService {

    /**
     *  根据 用户名查 用户拥有的角色
     * @param username
     * @return
     */
    List<String> queryRolesByUserName(String username);
}
