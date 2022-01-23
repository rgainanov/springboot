package ru.geekbrains.springboot.springboot.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.springboot.springboot.utils.Cart;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CartDto {
    private List<OrderItemDto> items;
    private Double totalPrice;

    public CartDto(Cart c) {
        this.items = c.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.totalPrice = c.getTotalPrice();
    }
}
