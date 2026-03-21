package dev.vinicius.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api/users")
public class UserController {

    @GET
    public String getUsers(){
        return "Hello world, Welcome to Quarkus!";
    }
}
