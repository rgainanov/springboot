package ru.geekbrains.springboot.springboot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.springboot.springboot.models.ProductCategory;
import ru.geekbrains.springboot.springboot.repositories.ProductCategoriesDAO;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductCategoriesService {
    private final ProductCategoriesDAO productCategoriesDAO;

    public List<ProductCategory> findAll() {
        return productCategoriesDAO.getAllCategories();
    }
}
