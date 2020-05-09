package com.nn.service.impl;

import com.nn.service.PermissionService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PermissionServiceImpl
 * @Author nn
 * @Date 2020/5/8 15:08
 */
public class PermissionServiceImpl implements PermissionService {
    @Override
    public List<String> queryPermissionsByUserName(String username) {
        List<String> permissionList = new ArrayList<>();
        permissionList.add("user:query");
        permissionList.add("user:delete");
        permissionList.add("user:update");
        permissionList.add("user:add");
        return permissionList;
    }
}
