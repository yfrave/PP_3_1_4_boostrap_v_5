package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String name) {
        return (Role) entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name").setParameter("name", name).getResultList().get(0);
    }
    @Override
    public Set<Role> findAll() {
        return new HashSet<>(entityManager.createQuery("FROM Role", Role.class).getResultList());
    }

    @Override
    @Transactional
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role findById(Long id) {

        return entityManager.find(Role.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

}