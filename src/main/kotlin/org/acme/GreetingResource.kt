package org.acme

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.utils.RUtil

@Path("/")
class GreetingResource {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    fun hello() = "Hello from RESTEasy Reactive"

    @GET
    @Path("/hello2")
    @Produces(MediaType.APPLICATION_JSON)
    fun hello2(): Response {
        return RUtil.expectationFailed(1, "что-то пошло не так");
    }

    @GET
    @Path("/hello3")
    @Produces(MediaType.APPLICATION_JSON)
    fun hello3(): Response {
        return RUtil.preconditionFailed(1, "что-то пошло не так");
    }
}