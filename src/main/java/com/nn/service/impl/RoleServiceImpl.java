package com.nn.service.impl;

import com.nn.service.RoleService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Author nn
 * @Date 2020/5/8 15:07
 */
public class RoleServiceImpl implements RoleService {
    @Override
    public List<String> queryRolesByUserName(String username) {
        List<String> rolesList = new ArrayList<>();
        rolesList.add("role1");
        rolesList.add("role2");
        rolesList.add("role3");
        rolesList.add("role4");
        return rolesList;
    }
}
