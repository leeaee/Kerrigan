package com.modoop.zerg.orochi.realm;

import com.modoop.zerg.orochi.cache.MemcachedObjectType;
import com.modoop.zerg.orochi.entity.admin.Admin;
import com.modoop.zerg.orochi.entity.admin.Role;
import com.modoop.zerg.orochi.service.dao.AdminShiroDao;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author: Genkyo Lee
 * @date: 5/4/12
 */
public class ShiroDbRealm extends AuthorizingRealm
{
    private static final String ALGORITHM = "MD5";
    private AdminShiroDao adminShiroDao;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
    {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Admin admin = adminShiroDao.findLoginAdmin(token.getUsername());
        if (admin != null)
        {
            return new SimpleAuthenticationInfo(admin.getName(), admin.getPassword(), getName());
        }
        else
        {
            String msg = "Realm was unable to find account data for the submitted AuthenticationToken " + token.getUsername() + ".";
            throw new UnknownAccountException(msg);
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        String username = (String) principals.fromRealm(getName()).iterator().next();
        Admin admin = adminShiroDao.findLoginAdmin(username);
        List<Role> roles = admin.getRoles();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Role role : roles)
        {
            List<String> permissions = adminShiroDao.findPermissionStringsByRoleId(role.getId());
            info.addStringPermissions(permissions);
        }
        return info;
    }

    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals)
    {
        return MemcachedObjectType.SHIRO_AUTHORIZATION.getPrefix() + principals;
    }

    @Override
    protected Object getAuthenticationCacheKey(AuthenticationToken token)
    {
        return token != null ? MemcachedObjectType.SHIRO_AUTHENTICATION.getPrefix() + token.getPrincipal() : null;
    }

    @PostConstruct
    public void initCredentialsMatcher()
    {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ALGORITHM);
        setCredentialsMatcher(matcher);
    }

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal)
    {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    @Autowired
    public void setAdminShiroDao(AdminShiroDao adminShiroDao)
    {
        this.adminShiroDao = adminShiroDao;
    }
} // end class
