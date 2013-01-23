package com.modoop.zerg.mamba.service.spi;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.modoop.zerg.mamba.entity.admin.Admin;
import com.modoop.zerg.mamba.entity.admin.Role;
import com.modoop.zerg.mamba.exception.EntityAlreadyExistException;
import com.modoop.zerg.mamba.exception.EntityCantDeleteException;
import com.modoop.zerg.mamba.exception.EntityNotFoundException;
import com.modoop.zerg.mamba.service.dao.AdminDao;
import com.modoop.zerg.taipan.core.mybatis.MyBatisDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: Genkyo Lee
 * @date: 4/28/12
 */
@Repository(value = "adminDao")
public class AdminDaoImpl extends MyBatisDao implements AdminDao
{
    //Admin
    @Override
    public Admin findAdmin(String name) throws EntityNotFoundException
    {
        Admin admin = getSqlSession().selectOne("adminMapper.findAdmin", name);
        if (admin == null) throw new EntityNotFoundException("Administrator", name);

        return admin;
    }

    @Override
    public Admin saveAdmin(Admin admin) throws EntityAlreadyExistException, EntityNotFoundException
    {
        Admin object = getSqlSession().selectOne("adminMapper.findAdmin", admin.getName());
        if (object != null) throw new EntityAlreadyExistException("Administrator", admin.getName());

        getSqlSession().insert("adminMapper.saveAdmin", admin);
        this.saveAdminXRole(admin.getId(), admin);

        return admin;
    }

    @Override
    public Admin updateAdmin(Admin admin) throws EntityNotFoundException
    {
        Admin entity = findAdmin(admin.getName());
        this.deleteAdminXRole(entity.getId());
        this.saveAdminXRole(entity.getId(), admin);

        if (admin.getId() == null) admin.setId(entity.getId());
        getSqlSession().update("adminMapper.updateAdmin", admin);

        return getSqlSession().selectOne("adminMapper.findAdmin", admin.getName());
    }

    @Override
    public void removeAdmin(String name) throws EntityNotFoundException, EntityCantDeleteException
    {
        Admin admin = this.findAdmin(name);
        if (admin.getId() == 1 || admin.getName().equals("admin")) throw new EntityCantDeleteException("Root administrator", "admin");

        this.deleteAdminXRole(admin.getId());
        getSqlSession().delete("adminMapper.removeAdmin", name);
    }

    //Role
    @Override
    public Role findRole(String name) throws EntityNotFoundException
    {
        Role role = getSqlSession().selectOne("adminMapper.findRole", name);
        if (role == null) throw new EntityNotFoundException("Role", name);

        return role;
    }

    //X
    private void saveAdminXRole(Long adminId, Admin newEntity) throws EntityNotFoundException
    {
        List<Map<String, Long>> roles = Lists.newArrayList();
        for (Role role : newEntity.getRoles())
        {
            Map<String, Long> map = Maps.newHashMap();
            map.put("adminId", adminId);
            map.put("roleId", this.findRole(role.getName()).getId());
            roles.add(map);
        }

        getSqlSession().insert("adminMapper.saveAdminXRole", roles);
    }

    public void deleteAdminXRole(Long id)
    {
        getSqlSession().insert("adminMapper.deleteAdminXRole", id);
    }
} // end class
