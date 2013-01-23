package com.modoop.zerg.mamba.webservice.rs;

import com.modoop.zerg.taipan.core.jersey.JerseyException;
import com.modoop.zerg.taipan.core.jersey.Jerseys;
import com.modoop.zerg.mamba.entity.admin.Admin;
import com.modoop.zerg.mamba.exception.EntityNotFoundException;
import com.modoop.zerg.mamba.service.AdminService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * @author: Genkyo Lee
 * @date: 4/19/12
 */
@Path("/admin")
@Component
public class AdminResouceService
{
    //Properties
    private static Logger logger = LoggerFactory.getLogger(AdminResouceService.class);
    private AdminService adminService;
    @Context private UriInfo uriInfo;

    //Methods
    @GET
    @Path("{name}")
    @RequiresPermissions("rs:admin:read")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Admin readAdmin(@PathParam("name") String name)
    {
        logger.debug("read admin: {}", name);
        try
        {
            return adminService.readAdmin(name);
        }
        catch (EntityNotFoundException e)
        {
            throw Jerseys.buildException(e.getStatus(), e.getMessage());
        }
        catch (RuntimeException e)
        {
            throw Jerseys.buildException(Response.Status.INTERNAL_SERVER_ERROR, e.getCause().getMessage());
        }
    }

    @POST
    @RequiresPermissions("rs:admin:write")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createAdmin(Admin admin)
    {
        logger.debug("create admin: {}", admin);
        try
        {
            Admin entity = adminService.createAdmin(admin);
            URI createdUri = uriInfo.getAbsolutePathBuilder().path(entity.getName()).build();
            return Response.created(createdUri).build();
        }
        catch (JerseyException e)
        {
            throw Jerseys.buildException(e.getStatus(), e.getMessage());
        }
        catch (RuntimeException e)
        {
            throw Jerseys.buildException(Response.Status.INTERNAL_SERVER_ERROR, e.getCause().getMessage());
        }
    }

    @PUT
    @RequiresPermissions("rs:admin:write")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateAdmin(Admin admin)
    {
        logger.debug("update admin: {}", admin);
        try
        {
            Admin entity = adminService.updateAdmin(admin);
            URI createdUri = uriInfo.getAbsolutePathBuilder().path(entity.getName()).build();
            return Response.created(createdUri).build();
        }
        catch (JerseyException e)
        {
            throw Jerseys.buildException(e.getStatus(), e.getMessage());
        }
        catch (RuntimeException e)
        {
            throw Jerseys.buildException(Response.Status.INTERNAL_SERVER_ERROR, e.getCause().getMessage());
        }
    }

    @DELETE
    @Path("{name}")
    @RequiresPermissions("rs:admin:write")
    public Response deleteAdmin(@PathParam("name") String name)
    {
        logger.debug("delete admin: {}", name);
        try
        {
            adminService.deleteAdmin(name);
            return Response.noContent().build();
        }
        catch (JerseyException e)
        {
            throw Jerseys.buildException(e.getStatus(), e.getMessage());
        }
        catch (RuntimeException e)
        {
            throw Jerseys.buildException(Response.Status.INTERNAL_SERVER_ERROR, e.getCause().getMessage());
        }
    }

    @Autowired
    public void setAdminService(AdminService adminService)
    {
        this.adminService = adminService;
    }
} // end class