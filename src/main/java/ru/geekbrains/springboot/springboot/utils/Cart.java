package ru.geekbrains.springboot.springboot.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.springboot.springboot.dtos.ProductDto;
import ru.geekbrains.springboot.springboot.exceptions.ResourceNotFoundException;
import ru.geekbrains.springboot.springboot.models.OrderItem;
import ru.geekbrains.springboot.springboot.models.Product;
import ru.geekbrains.springboot.springboot.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;


    @PostConstruct
    public void init() {
        this.items = new ArrayList<>();
    }

    public void addToCart(Long id) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(id)) {
                o.incrementQuantity();
                return;
            }
        }
        ProductDto p = productService.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Unable to find product with id: " + id + ".")
        );
    }
}
