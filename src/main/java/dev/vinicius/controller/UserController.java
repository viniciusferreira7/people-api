package dev.vinicius.controller;

import dev.vinicius.dto.UserRequestDto;
import dev.vinicius.dto.UserResponseDto;
import dev.vinicius.service.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Users", description = "Operations for managing users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Operation(summary = "Create a user", description = "Creates a new user and persists it to the database")
    @APIResponse(responseCode = "201", description = "User created",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = UserResponseDto.class)))
    @APIResponse(responseCode = "400", description = "Invalid request body")
    public Response createUser(UserRequestDto request) {
        UserResponseDto response = userService.create(request);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET
    @Operation(summary = "List users", description = "Returns a paginated list of users")
    @APIResponse(responseCode = "200", description = "List of users",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = UserResponseDto.class)))
    public Response findAll(
            @Parameter(description = "Page number (1-based)", example = "1")
            @QueryParam("page") @DefaultValue("1") Integer page,
            @Parameter(description = "Number of items per page", example = "10")
            @QueryParam("page_size") @DefaultValue("10") Integer pageSize) {

        List<UserResponseDto> users = userService.findAll(page, pageSize);
        return Response.ok(users).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Find user by ID", description = "Returns a single user by their UUID")
    @APIResponse(responseCode = "200", description = "User found",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = UserResponseDto.class)))
    @APIResponse(responseCode = "404", description = "User not found")
    public Response findById(
            @Parameter(description = "User UUID", required = true)
            @PathParam("id") String userId) {
        UserResponseDto userResponseDto = userService.findById(UUID.fromString(userId));

        return Response.ok(userResponseDto).build();
    }

    @PATCH
    @Path("/{id}")
    public Response update( @PathParam("id") String userId, UserRequestDto request){
        UserResponseDto userResponseDto = this.userService.update(UUID.fromString(userId), request);

        return Response.status(Response.Status.OK).entity(userResponseDto).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String userId){
        this.userService.delete(UUID.fromString(userId));

        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
