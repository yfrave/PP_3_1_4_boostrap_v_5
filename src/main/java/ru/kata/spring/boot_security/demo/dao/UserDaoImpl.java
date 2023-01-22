package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override

    public List<User> index() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    public User show(Long id) {

        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        return entityManager
                .createQuery("SELECT u FROM User u JOIN FETCH u.roles " +
                        "WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList().stream().findAny().orElse(null);
    }
    @Override
    @Transactional
    public void save(User user) {
        entityManager.joinTransaction();
        user.setRoles(user.getRoles());
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(Long id, User user) {
        entityManager.joinTransaction();
        User u = show(id);
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        entityManager.persist(u);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        entityManager.joinTransaction();
        try {
            entityManager.remove(show(id));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}