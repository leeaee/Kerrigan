package com.modoop.zerg.orochi.service;

import com.modoop.zerg.orochi.cache.MemcachedObjectType;
import com.modoop.zerg.orochi.entity.admin.Admin;
import com.modoop.zerg.orochi.entity.admin.Role;
import com.modoop.zerg.orochi.exception.EntityAlreadyExistException;
import com.modoop.zerg.orochi.exception.EntityCantDeleteException;
import com.modoop.zerg.orochi.exception.EntityNotFoundException;
import com.modoop.zerg.orochi.realm.ShiroDbRealm;
import com.modoop.zerg.orochi.service.dao.AdminDao;
import com.modoop.zerg.taipan.core.entity.page.Page;
import com.modoop.zerg.taipan.core.memcached.SpyMemcachedClient;
import com.modoop.zerg.taipan.core.util.DateTimeUtils;
import com.modoop.zerg.taipan.core.util.MD5Uitls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author: Genkyo Lee
 * @date: 1/10/13
 */
@Service
@Transactional
public class AdminService
{
    private SpyMemcachedClient memcachedClient;
    private AdminDao adminDao;
    private ShiroDbRealm shiroDbRealm;

    //Methods
    @Transactional(readOnly = true)
    public Page<Admin> readAdmins(Map<String, Object> parameters)
    {
        return adminDao.findAdmins(parameters);
    }

    @Transactional(readOnly = true)
    public Admin readAdmin(String name) throws EntityNotFoundException
    {
        Admin admin = memcachedClient.get(MemcachedObjectType.ADMIN.getPrefix() + name);
        if (admin == null)
        {
            admin = adminDao.findAdmin(name);
            memcachedClient.set(MemcachedObjectType.ADMIN.getPrefix() + name, admin, MemcachedObjectType.ADMIN.getExpiredTime());
        }

        return admin;
    }

    public Admin createAdmin(Admin admin) throws EntityAlreadyExistException, EntityNotFoundException
    {
        admin.setPassword(MD5Uitls.getHashString(admin.getPassword()));
        admin.setCreateTime(System.currentTimeMillis());
        admin.setLastModify(DateTimeUtils.TIME_OF_NA);

        return adminDao.saveAdmin(admin);
    }

    public Admin updateAdmin(Admin admin) throws EntityAlreadyExistException, EntityNotFoundException
    {
        if (admin.getPassword() != null) admin.setPassword(MD5Uitls.getHashString(admin.getPassword()));
        admin.setLastModify(System.currentTimeMillis());
        admin = adminDao.updateAdmin(admin);
        memcachedClient.replace(MemcachedObjectType.ADMIN.getPrefix() + admin.getName(), admin, MemcachedObjectType.ADMIN.getExpiredTime());
        shiroDbRealm.clearCachedAuthorizationInfo(admin.getName());

        return admin;
    }

    public void deleteAdmin(String name) throws EntityNotFoundException, EntityCantDeleteException
    {
        memcachedClient.safeDelete(MemcachedObjectType.ADMIN.getPrefix() + name);
        shiroDbRealm.clearCachedAuthorizationInfo(name);
        adminDao.removeAdmin(name);
    }

    public List<Role> getAdminRoles()
    {
        return adminDao.findRoles();
    }

    @Transactional(readOnly = true)
    public Role getAdminRole(String name) throws EntityNotFoundException
    {
        Role role = memcachedClient.get(MemcachedObjectType.ADMIN_ROLE.getPrefix() + name);
        if (role == null)
        {
            role = adminDao.findRole(name);
            memcachedClient.set(MemcachedObjectType.ADMIN_ROLE.getPrefix() + name, role, MemcachedObjectType.ADMIN_ROLE.getExpiredTime());
        }
        return role;
    }

    @Autowired
    public void setAdminDao(AdminDao adminDao)
    {
        this.adminDao = adminDao;
    }

    @Autowired
    public void setMemcachedClient(SpyMemcachedClient memcachedClient)
    {
        this.memcachedClient = memcachedClient;
    }

    @Autowired
    public void setShiroDbRealm(ShiroDbRealm shiroDbRealm)
    {
        this.shiroDbRealm = shiroDbRealm;
    }
} // end class
