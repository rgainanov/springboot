package ru.geekbrains.springboot.springboot.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.geekbrains.springboot.springboot.models.Product;
import ru.geekbrains.springboot.springboot.models.ProductCategory;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ProductCategoriesDAO {
    private SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        sessionFactory = GlobalSessionFactory.getSessionFactory();
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

    public ProductCategory findById(Long id) {
        ProductCategory productCategory;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            productCategory = session.get(ProductCategory.class, id);
            session.getTransaction().commit();
        }
        return productCategory;
    }
}
