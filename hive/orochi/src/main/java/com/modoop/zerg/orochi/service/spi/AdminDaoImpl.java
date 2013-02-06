package com.modoop.zerg.orochi.service.spi;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.modoop.zerg.orochi.entity.admin.Admin;
import com.modoop.zerg.orochi.entity.admin.Role;
import com.modoop.zerg.orochi.exception.EntityAlreadyExistException;
import com.modoop.zerg.orochi.exception.EntityCantDeleteException;
import com.modoop.zerg.orochi.exception.EntityNotFoundException;
import com.modoop.zerg.orochi.service.dao.AdminDao;
import com.modoop.zerg.taipan.core.entity.page.Page;
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
    public Page<Admin> findAdmins(Map<String, Object> parameters)
    {
        return selectPage("adminMapper.search", parameters);
    }

    @Override
    public Admin findAdmin(String name) throws EntityNotFoundException
    {
        Admin admin = getSqlSession().selectOne("adminMapper.findAdmin", name);
        if (admin == null) throw new EntityNotFoundException(Admin.KEY, name);

        return admin;
    }

    @Override
    public Admin saveAdmin(Admin admin)
    {
        getSqlSession().insert("adminMapper.saveAdmin", admin);
        this.saveAdminXRole(admin.getId(), admin);

        return admin;
    }

    @Override
    public Admin updateAdmin(Admin admin)
    {
        this.deleteAdminXRole(admin.getId());
        this.saveAdminXRole(admin.getId(), admin);

        getSqlSession().update("adminMapper.updateAdmin", admin);

        return getSqlSession().selectOne("adminMapper.findAdmin", admin.getName());
    }

    @Override
    public void removeAdmin(String name) throws EntityNotFoundException, EntityCantDeleteException
    {
        Admin admin = this.findAdmin(name);
        if (admin.getId() == 1 || admin.getName().equals("admin")) throw new EntityCantDeleteException(Admin.KEY, "admin");

        this.deleteAdminXRole(admin.getId());
        getSqlSession().delete("adminMapper.removeAdmin", name);
    }

    //Role
    @Override
    public List<Role> findRoles()
    {
        return getSqlSession().selectList("adminMapper.findRoles");
    }

    @Override
    public Role findRole(String name) throws EntityNotFoundException
    {
        Role role = getSqlSession().selectOne("adminMapper.findRole", name);
        if (role == null) throw new EntityNotFoundException("Role", name);

        return role;
    }

    //X
    private void saveAdminXRole(Long adminId, Admin newEntity)
    {
        List<Map<String, Long>> roles = Lists.newArrayList();
        for (Role role : newEntity.getRoles())
        {
            Map<String, Long> map = Maps.newHashMap();
            map.put("adminId", adminId);
            map.put("roleId", role.getId());
            roles.add(map);
        }

        getSqlSession().insert("adminMapper.saveAdminXRole", roles);
    }

    public void deleteAdminXRole(Long id)
    {
        getSqlSession().insert("adminMapper.deleteAdminXRole", id);
    }

    //Validator
    @Override
    public boolean checkAdminNotExist(String name) throws EntityAlreadyExistException
    {
        Admin object = getSqlSession().selectOne("adminMapper.findAdmin", name);
        if (object != null) throw new EntityAlreadyExistException(Admin.KEY, name);
        return true;
    }

} // end class
