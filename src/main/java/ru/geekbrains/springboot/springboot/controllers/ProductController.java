package ru.geekbrains.springboot.springboot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springboot.springboot.dtos.ProductDto;
import ru.geekbrains.springboot.springboot.exceptions.ResourceNotFoundException;
import ru.geekbrains.springboot.springboot.models.ProductCategory;
import ru.geekbrains.springboot.springboot.models.ProductImage;
import ru.geekbrains.springboot.springboot.repositories.specifications.ProductSpecifications;
import ru.geekbrains.springboot.springboot.services.ProductCategoriesService;
import ru.geekbrains.springboot.springboot.services.ProductImageService;
import ru.geekbrains.springboot.springboot.services.ProductService;
import ru.geekbrains.springboot.springboot.forms.NewProductUploadForm;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductCategoriesService productCategoriesService;
    private final ProductImageService productImageService;

    @GetMapping
    public Page<ProductDto> showAll(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "7") int size
    ) {

        return productService.findAll(ProductSpecifications.build(params), page, size);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findProductDtoById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product with id : " + id + " doesn't exist.")
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto addNewProduct(@ModelAttribute NewProductUploadForm form) {
        ProductDto p = new ProductDto();
        ProductCategory pg = productCategoriesService.findById(form.getProductCategoryId()).get();
        ProductImage pi = productImageService.insertOrUpdate(new ProductImage(form.getFiles()[0].getOriginalFilename()));
        p.setId(null);
        p.setTitle(form.getTitle());
        p.setPrice(form.getPrice());
        p.setPg(pg);
        p.setProductImage(pi);
        try {
            productImageService.saveImage(form.getFiles());
        } catch (IOException e) {
            e.printStackTrace();

        }
        return productService.insertOrUpdate(p);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@RequestBody ProductDto p) {
        return productService.insertOrUpdate(p);
    }
}
