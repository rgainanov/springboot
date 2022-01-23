package ru.geekbrains.springboot.springboot.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.springboot.springboot.models.OrderItem;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private String productTitle;
    private int quantity;
    private Double pricePerProduct;
    private Double price;

    public OrderItemDto(OrderItem o) {
        this.productTitle = o.getProduct().getTitle();
        this.quantity = o.getQuantity();
        this.pricePerProduct = o.getPricePerProduct();
        this.price = o.getPrice();
    }
}
