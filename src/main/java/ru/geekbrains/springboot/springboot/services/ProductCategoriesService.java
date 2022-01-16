package ru.geekbrains.springboot.springboot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.springboot.springboot.models.ProductCategory;
import ru.geekbrains.springboot.springboot.repositories.ProductCategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoriesService {
    private final ProductCategoryRepository productCategoryRepository;

    public Optional<ProductCategory> findById(Long id) {
        return productCategoryRepository.findById(id);
    }
}