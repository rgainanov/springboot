package ru.geekbrains.springboot.springboot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.springboot.springboot.models.Product;
import ru.geekbrains.springboot.springboot.repositories.ProductDAO;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductService {
    private final ProductDAO productDAO;

    public Product findById(Long id) {
        return productDAO.findById(id);
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public Product insertOrUpdate(Product p) {
        return productDAO.insertOrUpdate(p);
    }

    public void deleteById(Long id) {
        productDAO.deleteById(id);
    }
}
