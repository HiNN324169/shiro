package com.nn.relam.auth_cation;

import com.nn.domain.User;
import com.nn.service.UserService;
import com.nn.service.impl.UserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * @ClassName UserRelam
 * @Author nn
 * @Date 2020/5/8 10:42
 */
public class MyAuthenticationRealm extends AuthenticatingRealm {

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    private UserService userService = new UserServiceImpl();
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = authenticationToken.getPrincipal().toString();
        System.out.println("UserRealm。。。。。");
        User user = userService.queryUserById(principal);

        if (null != user){
            /**
             *  参数1：用户身份
             *  参数2：用户在数据库里面存的密码
             *  参数3：当前类名
             */
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo( user,user.getPassword() , this.getName());
//            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName);

            System.out.println(info);
            return info;
        }
        return null;
    }
}
