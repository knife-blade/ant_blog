package com.knife.ant.config.shiro.realm;

import com.knife.ant.business.user.entity.User;
import com.knife.ant.business.user.service.UserService;
import com.knife.ant.common.util.auth.JwtUtil;
import com.knife.ant.config.shiro.entity.AccountProfile;
import com.knife.ant.config.shiro.entity.JwtToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.Set;

public class AccountRealm extends AuthorizingRealm {
    @Lazy
    @Autowired
    private UserService userService;

    //使realm支持jwt的认证方案
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    // 登录认证
    // 此处的 SimpleAuthenticationInfo 可返回任意值，密码校验时不会用到它。
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;

        String userId = JwtUtil.getUserIdByToken((String) jwtToken.getPrincipal());
        if (userId == null) {
            throw new UnknownAccountException("token为空，请重新登录");
        }
        // 获取数据库中的密码
        User user = userService.getById(userId);
        if (user == null) {
            throw new UnknownAccountException("token为空，请重新登录");
        }

        AccountProfile accountProfile = new AccountProfile();
        accountProfile.setId(userId);
        accountProfile.setUserName(user.getUserName());

        String salt = user.getSalt();
        // 认证信息里存放账号密码, getName() 是当前Realm的继承方法，通常返回当前类名 :accountRealm
        // 盐也放进去，通过ShiroConfig里配置的 HashedCredentialsMatcher 进行自动校验
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                accountProfile, jwtToken.getCredentials(), ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
    }

    // 权限验证
    // 只有用到org.apache.shiro.web.filter.authz包里默认的过滤器才会走到这里。
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 能进入到这里，表示账号已经通过认证了
        AccountProfile profile = (AccountProfile) principalCollection.getPrimaryPrincipal();

        // 通过service获取角色和权限
        // Set<String> permissions = permissionService
        //         .getPermissionsByUserId(Long.parseLong(profile.getId()));
        // Set<String> roles = roleService.getRolesByUserId(profile.getId());

        Set<String> permissions = new HashSet<>();
        Set<String> roles = new HashSet<>();

        // 授权对象
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        // 把通过service获取到的角色和权限放进去
        s.setStringPermissions(permissions);
        s.setRoles(roles);
        return s;
    }
}
