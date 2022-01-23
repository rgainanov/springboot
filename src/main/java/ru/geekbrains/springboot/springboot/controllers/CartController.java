package ru.geekbrains.springboot.springboot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.springboot.springboot.dtos.CartDto;
import ru.geekbrains.springboot.springboot.utils.Cart;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;

    @GetMapping
    public CartDto getCart() {
        return new CartDto(cart);
    }

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cart.addToCart(id);
    }

    @GetMapping("/decrement/{id}")
    public void decrementProduct(@PathVariable Long id) {
        cart.removeFromCart(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cart.clear();
    }

}
