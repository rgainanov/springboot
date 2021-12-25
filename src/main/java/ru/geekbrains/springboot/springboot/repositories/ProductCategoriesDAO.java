package ru.geekbrains.springboot.springboot.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.geekbrains.springboot.springboot.models.Product;
import ru.geekbrains.springboot.springboot.models.ProductCategory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class ProductCategoriesDAO {
    private SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(ProductCategory.class)
                .buildSessionFactory();
    }

    public List<ProductCategory> getAllCategories() {
        List<ProductCategory> productCategories;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            productCategories = session.createQuery("SELECT p FROM ProductCategory p").getResultList();
            session.getTransaction().commit();
        }
        return productCategories;
    }

    @PreDestroy
    public void destroy() {
        sessionFactory.close();
    }
}
