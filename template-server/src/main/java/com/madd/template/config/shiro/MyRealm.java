package com.madd.template.config.shiro;

import com.madd.template.pojo.Role;
import com.madd.template.pojo.User;
import com.madd.template.service.PermissionService;
import com.madd.template.service.RoleService;
import com.madd.template.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public String getName() {
        return "MyRealm";
    }

    //用户登录认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //通过token获取使用户账号
        String username = (String)authenticationToken.getPrincipal();
        String password = new String((char[])authenticationToken.getCredentials());
        List<User> userList = userService.findByNamedParamList(User.builder().username(username).build());
        if (userList==null || userList.size()==0)return null;
        //todo(加密方式,需要加密的密码,盐值,加密次数)
        String nowPassword = new SimpleHash("MD5",password,"madd",1024).toString().toUpperCase();
        User testUser = userList.get(0);
        if(nowPassword.equals(testUser.getPassword())){
            Set<String>roleList = roleService.findByUserName(username);
            testUser.setRoles(roleList);
            SecurityUtils.getSubject().getSession().setAttribute("currentUser",testUser);
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }

    //为当前用户登录成功后授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String>testRoleSet = roleService.findByUserName(username);
        simpleAuthorizationInfo.setRoles(testRoleSet);
        Set<String>testPermissionSet = permissionService.findByUserName(username);
        simpleAuthorizationInfo.setStringPermissions(testPermissionSet);
        return simpleAuthorizationInfo;
    }

}

