package dev.vinicius.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(name = "UserResponse", description = "User data returned by the API")
public class UserResponseDto {

    @Schema(example = "550e8400-e29b-41d4-a716-446655440000")
    public UUID id;

    @Schema(example = "John Doe")
    public String name;

    @Schema(example = "john@example.com")
    public String email;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;
}
