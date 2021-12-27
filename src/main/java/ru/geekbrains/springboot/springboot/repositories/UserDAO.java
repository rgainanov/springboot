package ru.geekbrains.springboot.springboot.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.geekbrains.springboot.springboot.models.*;

import javax.annotation.PostConstruct;

@Component
public class UserDAO {
    private SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        sessionFactory = GlobalSessionFactory.getSessionFactory();
    }

    public User findByLogin(String login) {
        User user;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            user = session.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
            session.getTransaction().commit();
        }
        return user;
    }

    public User insertOrUpdate(User u) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            if (u.getId() != null) {
                User user = session.get(User.class, u.getId());
                user.setEmail(u.getEmail());
                user.setLogin(u.getLogin());
                user.setPassword(u.getPassword());
                user.setName(u.getName());
                user.setSurname(u.getSurname());
            } else {
                session.save(u);
            }
            session.getTransaction().commit();
        }
        return u;
    }
}
