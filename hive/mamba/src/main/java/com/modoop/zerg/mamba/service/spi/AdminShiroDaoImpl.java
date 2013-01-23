package com.modoop.zerg.mamba.service.spi;

import com.modoop.zerg.mamba.entity.admin.Admin;
import com.modoop.zerg.mamba.service.dao.AdminShiroDao;
import com.modoop.zerg.taipan.core.mybatis.MyBatisDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Genkyo Lee
 * @date: 8/22/12
 */
@Repository(value = "adminShiroDao")
public class AdminShiroDaoImpl extends MyBatisDao implements AdminShiroDao
{
    @Override
    public Admin findLoginAdmin(String name)
    {
        return getSqlSession().selectOne("adminMapper.findAdmin", name);
    }

    @Override
    public List<String> findPermissionStringsByRoleId(Long id)
    {
        return getSqlSession().selectList("adminMapper.findPermissionStringsByRoleId", id);
    }
}
