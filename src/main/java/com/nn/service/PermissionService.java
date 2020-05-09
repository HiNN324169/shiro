package com.nn.service;

import java.util.List;

/**
 * @ClassName PermissionService
 * @Author nn
 * @Date 2020/5/8 15:07
 */
public interface PermissionService {

    /**
     *  根据用户名查 用户拥有的权限
     * @param username
     * @return
     */
    List<String> queryPermissionsByUserName(String username);
}
