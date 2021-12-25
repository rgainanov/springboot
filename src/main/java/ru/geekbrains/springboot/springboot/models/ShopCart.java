package ru.geekbrains.springboot.springboot.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shopping_carts")
@Setter
@Getter
@NoArgsConstructor
public class ShopCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User cartUser;

    @OneToMany(mappedBy = "cart")
    private List<ShopCartItems> cartItems;

    @Column(name = "product_qty")
    private int productQty;

    @Override
    public String toString() {
        return "ShopCart{" +
                "id=" + id +
                ", cartUser=" + cartUser +
                ", productQty=" + productQty +
                '}';
    }
}