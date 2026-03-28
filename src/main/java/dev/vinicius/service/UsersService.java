package dev.vinicius.service;

import dev.vinicius.entity.Users;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UsersService {

    @Transactional
    public Users create(Users users){
        Users.persist(users);

        return users;
    }

    public List<Users> findAll(Integer page, Integer pageSize){
        Integer pageIndex = page - 1;

        if(pageIndex < 0){
            pageIndex = 0;
        }

        return Users.findAll().page(pageIndex, pageSize).list();
    }
}
