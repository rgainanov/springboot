package ru.geekbrains.springboot.springboot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.geekbrains.springboot.springboot.models.Product;
import ru.geekbrains.springboot.springboot.repositories.ProductRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product insertOrUpdate(Product p) {
        return productRepository.save(p);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Long getTotalProducts() {
        return productRepository.count();
    }

    public Page<Product> getPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


}
