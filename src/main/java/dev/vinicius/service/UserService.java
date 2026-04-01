package dev.vinicius.service;

import dev.vinicius.dto.UserRequestDto;
import dev.vinicius.dto.UserResponseDto;
import dev.vinicius.entity.User;
import dev.vinicius.exception.EmailAlreadyTakenException;
import dev.vinicius.exception.UserNotFoundException;
import dev.vinicius.mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
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

    public UserResponseDto findById(UUID userId) {
        Optional<User> user = User.findByIdOptional(userId);
        return user.map(userMapper::toResponse)
                .orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public UserResponseDto update(UUID userId, UserRequestDto request) {

        User user = (User) User.findByIdOptional(userId)
                .orElseThrow(UserNotFoundException::new);

        Optional<User> userWithSameEmail = User.find("email", request.email)
                .firstResultOptional();

        if (userWithSameEmail.isPresent() && !userWithSameEmail.get().id.equals(userId)) {
            throw new EmailAlreadyTakenException();
        }

        user.setName(request.name);
        user.setEmail(request.email);

        return userMapper.toResponse(user);
    }

    @Transactional
    public UserResponseDto delete(UUID userId) {
        User user = (User) User.findByIdOptional(userId)
                .orElseThrow(UserNotFoundException::new);

       User.deleteById(userId);

        return userMapper.toResponse(user);
    }
}
