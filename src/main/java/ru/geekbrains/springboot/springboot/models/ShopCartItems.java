package ru.geekbrains.springboot.springboot.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shop_cart_items")
@Setter
@Getter
@NoArgsConstructor
public class ShopCartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shop_cart_id")
    private ShopCart cart;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public String toString() {
        return "ShopCartItems{" +
                "id=" + id +
                ", product=" + product +
                '}';
    }
}
