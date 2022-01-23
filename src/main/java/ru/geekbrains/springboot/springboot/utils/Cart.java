package ru.geekbrains.springboot.springboot.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.springboot.springboot.exceptions.ResourceNotFoundException;
import ru.geekbrains.springboot.springboot.models.OrderItem;
import ru.geekbrains.springboot.springboot.models.Product;
import ru.geekbrains.springboot.springboot.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private Double totalPrice;


    @PostConstruct
    public void init() {
        this.items = new ArrayList<>();
    }

    public void addToCart(Long id) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(id)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
        Product p = productService.findProductById(id).orElseThrow(
                () -> new ResourceNotFoundException("Unable to find product with id: " + id + ".")
        );
        OrderItem orderItem = new OrderItem(p);
        items.add(orderItem);
        recalculate();
    }

    public void removeFromCart(Long id) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(id)) {
                o.decrementQuantity();
                recalculate();
                return;
            }
        }

        throw new ResourceNotFoundException("Product with id: " + id + " is not in your Cart");

    }

    public void clear() {
        items.clear();
        recalculate();
    }

    public void recalculate() {
        totalPrice = 0.0;
        for (OrderItem o : items) {
            totalPrice += o.getPrice();
        }
    }
}

