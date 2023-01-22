package ru.kata.spring.boot_security.demo.servises;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class RoleService {
    private final RoleDao roleDao;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Set<Role> findAll() {

        return roleDao.findAll().stream().collect(Collectors.toSet());
    }

    @Transactional
    public void save(Role role) {
        roleDao.save(role);
    }


    public void delete(Long id) {
        roleDao.delete(id);
    }



    public Role findById(Long id) {
        return roleDao.findById(id);
    }


    public Role findByName(String role) {
        return roleDao.getRoleByName(role);
    }


}
