package jm.security.example.dao;

import jm.security.example.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User getUserByName(String name) {
        User user = (User) entityManager.createQuery("from User u where u.name=:name")
                .setParameter("name", name).getSingleResult();

        return user;
    }

    @Override
    @Transactional
    public void add(User user) {
        // user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        User entity = findById(user.getId());

        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setPassword(user.getPassword());
        entity.setRoles(user.getRoles());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from User u where u.id=:id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }
}

