package ru.geekbrains.springboot.springboot.repositories;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.geekbrains.springboot.springboot.models.*;

import javax.annotation.PreDestroy;

@Component
public class GlobalSessionFactory {

    private static SessionFactory sessionFactory;

    private GlobalSessionFactory() {
    }

    public synchronized static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(ProductCategory.class)
                    .addAnnotatedClass(ShopCart.class)
                    .addAnnotatedClass(ShopCartItems.class)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    @PreDestroy
    public void destroy() {
        sessionFactory.close();
    }
}
