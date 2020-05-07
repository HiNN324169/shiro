package com.nn.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * @ClassName Test
 * @Author nn
 * @Date 2020/5/6 14:29
 *
 *  Authentication 认证
 */
public class Authentication {

    public static void main(String[] args) {
        String username = "zhangsan";
        String password = "1234562";

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
            System.out.println("是否认证成功："+authenticated);
        }catch (AuthenticationException e){
            System.out.println("用户名或者密码错误");
        }
    }
}
