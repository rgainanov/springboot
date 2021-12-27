package ru.geekbrains.springboot.springboot.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.geekbrains.springboot.springboot.models.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ShopCartDAO {
    private SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(ShopCart.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(ShopCartItems.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(ProductCategory.class)
                .buildSessionFactory();

    // test table relations
//        try (Session session = sessionFactory.getCurrentSession()) {
//            session.beginTransaction();
//
//            ShopCart shopCart = session.get(ShopCart.class, 1L);
//            session.getTransaction().commit();
//        }
    }


    @PreDestroy
    public void destroy() {
        sessionFactory.close();
    }
}
