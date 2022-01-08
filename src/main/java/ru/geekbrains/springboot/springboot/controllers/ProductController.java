package ru.geekbrains.springboot.springboot.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springboot.springboot.models.Product;
import ru.geekbrains.springboot.springboot.models.ProductCategory;
import ru.geekbrains.springboot.springboot.services.ProductCategoriesService;
import ru.geekbrains.springboot.springboot.services.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductCategoriesService productCategoriesService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> showAll(
            @RequestParam(name = "product_category", required = false) Long productCategoryId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "7") int size
    ) {
        Pageable paging = PageRequest.of(page - 1, size);
        Page<Product> productPage = productService.getPage(paging);
        List<Product> out = productPage.getContent();
//
        if (productCategoryId != null) {
            out = out.stream().filter(p -> p.getPg().getId().equals(productCategoryId)).collect(Collectors.toList());
        }

        int totalPages = productPage.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("products", out);
        response.put("currentPage", productPage.getNumber());
        response.put("totalItems", productPage.getTotalElements());
        response.put("pageNumbers", pageNumbers);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addNewProduct(@RequestBody Product p) {
        p.setId(null);
        return productService.insertOrUpdate(p);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product p) {
        return productService.insertOrUpdate(p);
    }
}
