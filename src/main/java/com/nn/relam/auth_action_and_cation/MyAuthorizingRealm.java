package com.nn.relam.auth_action_and_cation;

import com.nn.domain.ActiveUser;
import com.nn.domain.User;
import com.nn.service.PermissionService;
import com.nn.service.RoleService;
import com.nn.service.UserService;
import com.nn.service.impl.PermissionServiceImpl;
import com.nn.service.impl.RoleServiceImpl;
import com.nn.service.impl.UserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.List;

/**
 * @ClassName MyAuthorizingRealm
 * @Author nn
 * @Date 2020/5/8 14:44
 */
public class MyAuthorizingRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    private UserService userService = new UserServiceImpl();

    private RoleService roleService = new RoleServiceImpl();

    private PermissionService permissionService = new PermissionServiceImpl();


    /**
     *  自定义认证
     *  认证只会调用一次
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证方法被调用...");
        String username = authenticationToken.getPrincipal().toString();
        User user = userService.queryUserById(username);

        if(null != user) {

            // 根据用户名查寻用户拥有哪些角色
            List<String> rolesList =  roleService.queryRolesByUserName(username);

            // 根据用户名查询 用户拥有哪些权限
            List<String> permissionList = permissionService.queryPermissionsByUserName(username);

            ActiveUser activeUser = new ActiveUser(user,rolesList,permissionList);
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(activeUser, user.getPassword(), this.getName());

            return authenticationInfo;
        }
        return null;
    }

    /**
     *  自定义授权
     *  每 调用一次 权限判断 就会执行 一次 doGetAuthorizationInfo 授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权方法被调用...");
        ActiveUser activeUser = (ActiveUser)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (activeUser.getUser().getType() == 0){
            simpleAuthorizationInfo.addStringPermission("*:*");
        }else{
            //当前用户所拥有的权限
            List<String> permissionList = activeUser.getPermissionList();
            if (permissionList!=null && permissionList.size()>0){
                simpleAuthorizationInfo.addStringPermissions(permissionList);
            }

            //当前用户所拥有的的角色
            List<String> rolesList = activeUser.getRolesList();
            if (rolesList!=null && rolesList.size()>0){
                simpleAuthorizationInfo.addRoles(rolesList);
            }

        }
        return simpleAuthorizationInfo;
    }
}
