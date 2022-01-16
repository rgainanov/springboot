package ru.geekbrains.springboot.springboot.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.springboot.springboot.models.Product;
import ru.geekbrains.springboot.springboot.models.ProductCategory;
import ru.geekbrains.springboot.springboot.models.ProductImage;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private Double price;
    private ProductCategory pg;
    private ProductImage productImage;

    public ProductDto(Product p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
        this.pg = p.getPg();
        this.productImage = p.getProductImage();
    }
}