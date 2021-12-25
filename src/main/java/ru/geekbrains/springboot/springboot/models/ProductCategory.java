package ru.geekbrains.springboot.springboot.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_category")
@Setter
@Getter
@NoArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "pg")
    private List<Product> products;

    @Column(name = "product_category")
    private String productCategory;

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", productCategory='" + productCategory + '\'' +
                '}';
    }
}
