package dev.vinicius.controller;

import dev.vinicius.entity.Users;
import dev.vinicius.service.UsersService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @POST
    public Response createUser(Users users){
        this.usersService.create(users);

        return Response.status(Response.Status.CREATED).entity(users).build();
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("1") Integer page, @QueryParam("page_size") @DefaultValue("10") Integer pageSize){
        List<Users> usersList = this.usersService.findAll(page, pageSize);

        return Response.status(Response.Status.OK).entity(usersList).build();
    }
}
