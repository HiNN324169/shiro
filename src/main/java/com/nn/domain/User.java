package com.nn.domain;

/**
 * @ClassName User
 * @Author nn
 * @Date 2020/5/8 11:17
 */
public class User {

    private String username;
    private String password;
    private String sex;

    /**
     *  1 : 普通用户
     *  0 : 超级管理员
     */
    private Integer type=1;

    public User() {
    }

    public User(String username, String password, String sex, Integer type) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", type=" + type +
                '}';
    }
}
