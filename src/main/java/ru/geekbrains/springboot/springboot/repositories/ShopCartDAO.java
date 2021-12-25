package ru.geekbrains.springboot.springboot.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.geekbrains.springboot.springboot.models.Product;
import ru.geekbrains.springboot.springboot.models.ShopCart;
import ru.geekbrains.springboot.springboot.models.ShopCartItems;
import ru.geekbrains.springboot.springboot.models.User;

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
                .buildSessionFactory();

    // test table relations
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            ShopCart shopCart = session.get(ShopCart.class, 1L);
            System.out.println(shopCart);
            System.out.println(shopCart.getCartUser());
            session.getTransaction().commit();
        }
    }


    @PreDestroy
    public void destroy() {
        sessionFactory.close();
    }
}
