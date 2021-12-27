package ru.geekbrains.springboot.springboot.repositories;

import org.springframework.stereotype.Component;
import ru.geekbrains.springboot.springboot.models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ProductDAO {
    private SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        sessionFactory = GlobalSessionFactory.getSessionFactory();
    }

    public List<Product> findAll() {
        List<Product> products;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            products = session.createQuery("SELECT p FROM Product p").getResultList();
            session.getTransaction().commit();
        }
        return products;
    }

    public Product insertOrUpdate(Product p) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            if (p.getId() != null) {
                Product product = session.get(Product.class, p.getId());
                product.setTitle(p.getTitle());
                product.setPrice(p.getPrice());
                product.setPg(p.getPg());
            } else {
                session.save(p);
            }

            session.getTransaction().commit();
        }
        return p;
    }

    public Product findById(Long id) {
        Product product;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            product = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return product;
    }

    public void deleteById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from Product p where p.id=:id", Product.class)
                    .setParameter("id", id);
            session.getTransaction().commit();
        }
    }
}
