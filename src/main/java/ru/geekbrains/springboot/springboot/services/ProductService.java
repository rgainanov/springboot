package ru.geekbrains.springboot.springboot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.springboot.springboot.models.Product;
import ru.geekbrains.springboot.springboot.repositories.ProductsInMemoryRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductService {
    private final ProductsInMemoryRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product insertOrUpdate(Product p) {
        return productRepository.insertOrUpdate(p);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
