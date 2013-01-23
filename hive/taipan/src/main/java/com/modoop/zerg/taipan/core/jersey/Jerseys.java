package com.modoop.zerg.taipan.core.jersey;

import com.modoop.zerg.taipan.core.entity.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * @author: Genkyo Lee
 * @date: 4/29/12
 */
public class Jerseys
{
    private static Logger logger = LoggerFactory.getLogger(Jerseys.class);

    /**
     * Build webApplicationException with stander http status and log information.
     */
    public static WebApplicationException buildException(Status status, String message)
    {
        return buildException(status.getStatusCode(), message);
    }

    /**
     * Build webApplicationException with http status code and log information.
     */
    public static WebApplicationException buildException(int status, String message)
    {
        logger.error("RESTful service error " + status + ": " + message);
        return new WebApplicationException(buildErrorResponse(status, message));
    }

    /**
     * Create text/plain format return response.
     *
     * @param status
     * @param message
     * @return
     */
    public static Response buildErrorResponse(Status status, String message)
    {
        return buildErrorResponse(status.getStatusCode(), message);
    }

    public static Response buildErrorResponse(int status, String message)
    {
        com.modoop.zerg.taipan.core.entity.Error error = new Error(status, message);
        return Response.status(status).entity(error).build();
    }

    /**
     * Create status 500 WebApplicatonExcetpion and log RuntimeException
     * if RuntimeException is WebApplicatonExcetpion, ignored.
     */
    public static WebApplicationException buildDefaultException(RuntimeException e)
    {
        if (e instanceof WebApplicationException)
        {
            return (WebApplicationException) e;
        }
        else
        {
            logger.error("RESTful service error 500: " + e.getMessage(), e);
            return new WebApplicationException();
        }
    }
}
