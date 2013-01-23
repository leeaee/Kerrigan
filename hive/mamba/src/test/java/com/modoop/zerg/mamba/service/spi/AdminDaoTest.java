package com.modoop.zerg.mamba.service.spi;

import com.modoop.zerg.mamba.exception.EntityNotFoundException;
import com.modoop.zerg.mamba.service.dao.AdminDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * @author: Genkyo Lee
 * @date: 7/13/12
 */
@DirtiesContext
@ContextConfiguration(locations = { "/spring/application.context.xml" })
public class AdminDaoTest extends AbstractTransactionalJUnit4SpringContextTests
{
    @Autowired
    private AdminDao adminDao;

    @Test
    public void testGetRole() throws EntityNotFoundException
    {
        System.out.println(adminDao.findRole("Guest"));
    }
}
