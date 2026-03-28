package dev.vinicius.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "UserRequest", description = "Payload to create a new user")
public class UserRequestDto {

    @Schema(required = true, example = "John Doe")
    public String name;

    @Schema(required = true, example = "john@example.com")
    public String email;
}
