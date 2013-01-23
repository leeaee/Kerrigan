package com.modoop.zerg.orochi.service.dao;

import com.modoop.zerg.orochi.entity.admin.Admin;

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
