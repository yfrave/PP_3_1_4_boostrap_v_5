package ru.kata.spring.boot_security.demo.dao;



import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {

    List<User> index();

    User show(Long id);

    User findByUsername(String username);


    @Transactional
    void save(User user);

    void update(Long id, User updatedUser);

    void delete(Long id);
}