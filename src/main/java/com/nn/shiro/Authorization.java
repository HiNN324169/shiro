package com.nn.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Authorization
 * @Author nn
 * @Date 2020/5/6 15:21
 * Authorization 授权
 */
public class Authorization {

    public static void main(String[] args) {
        String username = "zhangsan";
        String password = "123456";

        //1、创建SecurityManager 工程，不要使用JDK内的SecurityManager类，JDK 中也有这个类，在java.lang 包下
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //2、从工厂中获取 SecurityManager
        SecurityManager securityManager = factory.getInstance();

        //3、把当前的SecurityManager绑定到当前线程
        SecurityUtils.setSecurityManager(securityManager);

        //4、取出当前Subject
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        /**
         *  抛出异常：
         *  1、用户名错误：UnknownAccountException
         *  2、密码错误：IncorrectCredentialsException
         */
        try {
            subject.login(token);
            boolean authenticated = subject.isAuthenticated();
            System.out.println("是否认证成功：" + authenticated);
        } catch (AuthenticationException e) {
            System.out.println("用户名或者密码错误");
        }

        /**
         *  判断 角色
         */
        // 1、 判断用户是否有某一个角色
        boolean role1 = subject.hasRole("role1");
        System.out.println(username + "拥有role1的角色？" + role1);
        //zhangsan拥有role1的角色？true

        // 2、判断用户是否同时拥有 collection 集合里的所有角色
        List<String> collection = new ArrayList<>();
        collection.add("role1");
        collection.add("role2");
        boolean b = subject.hasAllRoles(collection);
        System.out.println(username + "拥有" + collection + "的角色？" + b);
        //zhangsan拥有[role1, role2]的角色？false

        // 3、分别判断当前用户是否 拥有 集合 collection 里面的角色
        boolean[] booleans = subject.hasRoles(collection);
        System.out.println(username + "分别拥有" + collection + "的角色？" + Arrays.toString(booleans));
        // zhangsan分别拥有[role1, role2]的角色？[true, false]


        /**
         *  判断权限
         */

        // 1、判断当前用户是否有某权限
        boolean permitted = subject.isPermitted("user:query");
        System.out.println("判断当前用户是否有user:query 权限：" + permitted);
        //判断当前用户是否有user:query 权限：true

        // 2、 分别判断当前用户是否拥有数组roles里的权限
        String[] permissions = {"user:query", "user:update", "user:delete", "user:xxx"};
        boolean[] permitteds = subject.isPermitted(permissions);
        System.out.println(username + "分别拥有集合：" + Arrays.toString(permitteds) + "的权限：" + Arrays.toString(permitteds));
        //zhangsan分别拥有集合：[true, true, true, false]的权限：[true, true, true, false]

        // 3、判断 用户 是否有数组里的所有权限
        boolean permittedAll = subject.isPermittedAll(permissions);
        System.out.println("判断 用户 是否有数组里的所有权限：" + permittedAll);
    }
}
