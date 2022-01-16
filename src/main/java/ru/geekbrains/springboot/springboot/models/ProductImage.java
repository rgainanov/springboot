package ru.geekbrains.springboot.springboot.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product_images")
@Setter
@Getter
@NoArgsConstructor
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image_path")
    private String image_path;

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", image_path='" + image_path + '\'' +
                '}';
    }
}
