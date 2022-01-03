package ru.geekbrains.springboot.springboot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.springboot.springboot.models.ProductCategory;
import ru.geekbrains.springboot.springboot.repositories.ProductCategoryRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductCategoriesService {
    private final ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    public ProductCategory findById(Long id) {
        return productCategoryRepository.findById(id).get();
    }
}
