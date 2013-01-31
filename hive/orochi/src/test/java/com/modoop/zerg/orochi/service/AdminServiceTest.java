package com.modoop.zerg.orochi.service;

import com.google.common.collect.Lists;
import com.modoop.zerg.orochi.entity.admin.Admin;
import com.modoop.zerg.orochi.entity.admin.Role;
import com.modoop.zerg.orochi.exception.EntityAlreadyExistException;
import com.modoop.zerg.orochi.exception.EntityNotFoundException;
import com.modoop.zerg.taipan.core.jersey.JerseyException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author: YiLi
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
        for (int i = 0; i < 100; i ++)
        {
            Admin admin = new Admin("test_" + i);
            admin.setPassword("111111");
            admin.setTrueName("tester_" + i);
            admin.setRoles(Lists.newArrayList(new Role(2L)));
            try
            {
                System.out.println(adminService.createAdmin(admin));
            }
            catch (EntityAlreadyExistException e)
            {
                System.out.println(e.getMessage());
            }
            catch (EntityNotFoundException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
}
