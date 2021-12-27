package ru.geekbrains.springboot.springboot.repositories;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ShopCartDAO {
    private SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        sessionFactory = GlobalSessionFactory.getSessionFactory();
    }


}
