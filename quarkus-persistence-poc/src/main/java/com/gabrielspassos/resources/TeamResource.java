package com.gabrielspassos.resources;

import com.gabrielspassos.domain.SoccerTeam;
import com.gabrielspassos.service.TeamService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Optional;

@Path("/v1/teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamResource {

    @Inject
    private TeamService service;

    @GET
    public List<SoccerTeam> findAll() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Optional<SoccerTeam> get(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    public SoccerTeam save(SoccerTeam team) {
        return service.save(team);
    }

    @DELETE
    @Path("/{id}")
    public boolean delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}