package com.modoop.zerg.orochi.service.dao;


import com.modoop.zerg.orochi.entity.admin.Admin;
import com.modoop.zerg.orochi.entity.admin.Role;
import com.modoop.zerg.orochi.exception.EntityAlreadyExistException;
import com.modoop.zerg.orochi.exception.EntityCantDeleteException;
import com.modoop.zerg.orochi.exception.EntityNotFoundException;
import com.modoop.zerg.taipan.core.entity.page.Page;

import java.util.List;
import java.util.Map;

/**
 * @author: Genkyo Lee
 * @date: 4/28/12
 */
public interface AdminDao
{
    //Admin
    public Page<Admin> findAdmins(Map<String, Object> parameters);

    public Admin findAdmin(String name) throws EntityNotFoundException;

    public Admin saveAdmin(Admin admin) throws EntityAlreadyExistException, EntityNotFoundException;

    public Admin updateAdmin(Admin admin);

    public void removeAdmin(String name) throws EntityNotFoundException, EntityCantDeleteException;

    //Role
    public List<Role> findRoles();

    public Role findRole(String name) throws EntityNotFoundException;
}
