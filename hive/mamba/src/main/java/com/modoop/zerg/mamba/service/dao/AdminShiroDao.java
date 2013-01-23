package com.modoop.zerg.mamba.service.dao;

import com.modoop.zerg.mamba.entity.admin.Admin;

import java.util.List;

/**
 * @author: Genkyo Lee
 * @date: 8/22/12
 */
public interface AdminShiroDao
{
    public Admin findLoginAdmin(String name);

    public List<String> findPermissionStringsByRoleId(Long id);
}
