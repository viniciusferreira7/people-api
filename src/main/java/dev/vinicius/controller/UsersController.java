package dev.vinicius.controller;

import dev.vinicius.entity.Users;
import dev.vinicius.service.UsersService;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

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
}
