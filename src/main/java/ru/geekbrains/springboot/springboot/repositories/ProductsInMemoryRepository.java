package ru.geekbrains.springboot.springboot.repositories;

import org.springframework.stereotype.Component;
import ru.geekbrains.springboot.springboot.models.Product;

import javax.annotation.PostConstruct;
import java.util.*;


@Component
public class ProductsInMemoryRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>();
        this.products.add(new Product(1L, "Milk", 0.6F));
        this.products.add(new Product(2L, "Bread", 1.6F));
        this.products.add(new Product(3L, "Meat", 2.6F));
    }

    public Product insertOrUpdate(Product p) {
        if (p.getId() != null) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(p.getId())) {
                    products.set(i, p);
                    return p;
                }
            }
        }

        Long newId = products.stream().mapToLong(Product::getId).max().orElseGet(() -> 0L) + 1L;
        p.setId(newId);
        products.add(p);
        return p;
    }

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Long id) {
        return products.stream().
                filter(p -> p.getId().equals(id)).
                findFirst();
    }

    public void deleteById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

}
