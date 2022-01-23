package ru.geekbrains.springboot.springboot.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItem {
    private Product product;
    private int quantity;
    private Double pricePerProduct;
    private Double price;

    public OrderItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = this.quantity * this.pricePerProduct;
    }

    public void incrementQuantity() {
        quantity++;
        price = quantity * pricePerProduct;
    }

    public void decrementQuantity() {
        quantity--;
        if (quantity <= 0) {

        }
        price = quantity * pricePerProduct;
    }
}
