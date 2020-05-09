package com.nn.domain;

import java.util.List;

/**
 * @ClassName ActiveUser
 * @Author nn
 * @Date 2020/5/8 15:09
 */
public class ActiveUser {

    private User user;
    private List<String> rolesList;
    private List<String> permissionList;

    public ActiveUser(User user, List<String> rolesList, List<String> permissionList) {
        this.user = user;
        this.permissionList = permissionList;
        this.rolesList = rolesList;
    }

    public ActiveUser() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<String> rolesList) {
        this.rolesList = rolesList;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String toString() {
        return "ActiveUser{" +
                "user=" + user +
                ", rolesList=" + rolesList +
                ", permissionList=" + permissionList +
                '}';
    }
}
