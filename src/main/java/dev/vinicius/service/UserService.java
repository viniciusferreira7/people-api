package dev.vinicius.service;

import dev.vinicius.dto.UserRequestDto;
import dev.vinicius.dto.UserResponseDto;
import dev.vinicius.entity.User;
import dev.vinicius.mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsersService {

    private final UserMapper userMapper;

    public UsersService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponseDto create(UserRequestDto request) {
        User user = userMapper.toEntity(request);
        User.persist(user);
        return userMapper.toResponse(user);
    }

    public List<UserResponseDto> findAll(Integer page, Integer pageSize) {
        Integer pageIndex = page - 1;

        if (pageIndex < 0) {
            pageIndex = 0;
        }

        return User.findAll()
                .page(pageIndex, pageSize)
                .<User>list()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    public Optional<UserResponseDto> findById(String userId) {
        User user = User.findById(userId);
        return Optional.ofNullable(user).map(userMapper::toResponse);
    }
}
