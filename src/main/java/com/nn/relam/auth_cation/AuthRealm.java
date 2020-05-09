package com.nn.relam.auth_cation;

import com.nn.relam.auth_cation.MyAuthenticationRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * @ClassName AuthRealm
 * @Author nn
 * @Date 2020/5/8 10:45
 */
public class AuthRealm {

    public static void main(String[] args) {

        String username = "zhangsan";
        String password = "123456";

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        DefaultSecurityManager securityManager = (DefaultSecurityManager) factory.getInstance();

//        使用自定义 realm
        securityManager.setRealm(new MyAuthenticationRealm());

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            boolean authenticated = subject.isAuthenticated();
            System.out.println("是否认证成功："+authenticated);

            /**
             *  登入成功回调方法
             *  得到 doGetAuthenticationInfo 的返回值 SimpleAuthenticationInfo 里面的第一个参数值
             */
            Object principal = subject.getPrincipal();
            System.out.println(principal);
        }catch (AuthenticationException e){
            System.out.println("用户名或者密码错误");
        }


    }
}
