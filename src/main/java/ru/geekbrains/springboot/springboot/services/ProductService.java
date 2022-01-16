package ru.geekbrains.springboot.springboot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.springboot.springboot.dtos.ProductDto;
import ru.geekbrains.springboot.springboot.models.Product;
import ru.geekbrains.springboot.springboot.repositories.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(ProductDto::new);
    }


    public Page<ProductDto> findAll(Specification<Product> spec, int page, int size) {
        return productRepository.findAll(spec, PageRequest.of(page - 1, size)).map(ProductDto::new);

    }

    public ProductDto insertOrUpdate(ProductDto p) {
        Product product;
        if (p.getId() != null) {
            product = productRepository.findById(p.getId()).get();
        } else {
            product = new Product();
        }
        product.setTitle(p.getTitle());
        product.setPrice(p.getPrice());
        product.setPg(p.getPg());
        Product savedProduct = productRepository.save(product);
        p.setId(savedProduct.getId());
        return p;
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Long getTotalProducts() {
        return productRepository.count();
    }

}
