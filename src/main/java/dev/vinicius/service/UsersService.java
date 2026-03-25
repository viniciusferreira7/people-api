package dev.vinicius.service;

import dev.vinicius.entity.Users;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsersService {

    @Transactional
    public Users create(Users users){
        Users.persist(users);

        return users;
    }
}
