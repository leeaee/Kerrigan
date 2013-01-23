package com.modoop.zerg.taipan.core.shiro;

import com.modoop.zerg.taipan.core.jersey.Jerseys;
import org.apache.shiro.authz.UnauthorizedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author: Genkyo Lee
 * @date: 9/13/12
 */
@Provider
public class ShiroExceptionMapper implements ExceptionMapper<UnauthorizedException>
{
    @Override
    public Response toResponse(UnauthorizedException exception)
    {
        return Jerseys.buildErrorResponse(Response.Status.UNAUTHORIZED, exception.getMessage());
    }
} // end class
