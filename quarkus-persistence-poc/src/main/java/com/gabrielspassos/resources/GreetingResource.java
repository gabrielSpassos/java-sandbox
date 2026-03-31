package com.gabrielspassos.resources;

import com.gabrielspassos.model.Greeting;
import com.gabrielspassos.service.GreetingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/v1/greet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @Inject
    private GreetingService service;

    @GET
    @Path("/{name}")
    public Greeting greet(@PathParam("name") String name) {
        return new Greeting(service.greet(name));
    }

}
