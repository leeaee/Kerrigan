package com.modoop.zerg.mamba.service.dao;

import com.modoop.zerg.mamba.entity.admin.Admin;
import com.modoop.zerg.mamba.entity.admin.Role;
import com.modoop.zerg.mamba.exception.EntityAlreadyExistException;
import com.modoop.zerg.mamba.exception.EntityCantDeleteException;
import com.modoop.zerg.mamba.exception.EntityNotFoundException;

/**
 * @author: Genkyo Lee
 * @date: 4/28/12
 */
public interface AdminDao
{
    //Admin
    public Admin findAdmin(String name) throws EntityNotFoundException;

    public Admin saveAdmin(Admin admin) throws EntityAlreadyExistException, EntityNotFoundException;

    public Admin updateAdmin(Admin admin) throws EntityNotFoundException;

    public void removeAdmin(String name) throws EntityNotFoundException, EntityCantDeleteException;

    //Role
    public Role findRole(String name) throws EntityNotFoundException;
}
