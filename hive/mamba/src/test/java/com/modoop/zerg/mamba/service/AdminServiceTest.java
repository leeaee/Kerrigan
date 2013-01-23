package com.modoop.zerg.mamba.service;

import com.google.common.collect.Lists;
import com.modoop.zerg.mamba.entity.admin.Admin;
import com.modoop.zerg.mamba.entity.admin.Role;
import com.modoop.zerg.mamba.exception.EntityCantDeleteException;
import com.modoop.zerg.mamba.exception.EntityNotFoundException;
import com.modoop.zerg.taipan.core.jersey.JerseyException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author: Genkyo Lee
 * @date: 1/5/13
 */
@DirtiesContext
@ContextConfiguration(locations = { "/spring/application.context.xml" })
public class AdminServiceTest extends AbstractJUnit4SpringContextTests
{
    @Autowired
    private AdminService adminService;

    @Test
    public void testCreateAdmin()
    {
        Admin admin = new Admin("roger");
        admin.setPassword("111111");
        admin.setTrueName("genkyo");
        admin.setState(0);
        admin.setRoles(Lists.newArrayList(new Role("Guest")));
        try
        {
            System.out.println(adminService.createAdmin(admin));
        }
        catch (JerseyException e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void testUpdateAdmin()
    {
        try
        {
            Admin admin = new Admin("roger");
            admin.setPassword("222222");
            admin.setEmail("roger@gmail.com");
            List<Role> roles = Lists.newArrayList();
            roles.add(new Role("Guest"));
            roles.add(new Role("Administrator"));
            admin.setRoles(roles);
            System.out.println(adminService.updateAdmin(admin));
        }
        catch (JerseyException e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void testReadAdmin()
    {
        try
        {
            assertNotNull(adminService.readAdmin("admin"));
            System.out.println(adminService.readAdmin("roger"));
        }
        catch (JerseyException e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void testDeleteAdmin()
    {
        try
        {
            adminService.deleteAdmin("roger");
        }
        catch (EntityNotFoundException e)
        {
            System.out.println(e.getLocalizedMessage());
        }
        catch (EntityCantDeleteException e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

} // end class
