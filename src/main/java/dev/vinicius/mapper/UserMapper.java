package dev.vinicius.mapper;

import dev.vinicius.dto.UserRequestDto;
import dev.vinicius.dto.UserResponseDto;
import dev.vinicius.entity.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMapper {

    public User toEntity(UserRequestDto dto) {
        User user = new User();
        user.name = dto.name;
        user.email = dto.email;
        return user;
    }

    public UserResponseDto toResponse(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.id = user.id;
        dto.name = user.name;
        dto.email = user.email;
        dto.createdAt = user.createdAt;
        dto.updatedAt = user.updatedAt;
        return dto;
    }
}
