package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.Set;

public interface RoleDao {

    Set<Role> findAll();

    void save(Role role);

    Role findById(Long id);

    void delete(Long id);

    Role getRoleByName(String name);
}
