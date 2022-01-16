package ru.geekbrains.springboot.springboot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.springboot.springboot.models.ProductImage;
import ru.geekbrains.springboot.springboot.repositories.ProductImageRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ProductImageService {
    private final ProductImageRepository productImageRepository;

    public String saveImage(MultipartFile[] files) throws IOException {
        Path path = Paths.get("/Users/gainanov/Downloads/springboot/images");

        StringBuilder sb = new StringBuilder();

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }

            byte[] bytes = file.getBytes();
            path = path.resolve(file.getOriginalFilename());
            Files.write(path, bytes);

            sb.append(path).append(",");
        }
        return sb.toString();
    }

    public ProductImage insertOrUpdate(ProductImage pi) {
        ProductImage productImage;
        if (pi.getId() != null) {
            productImage = productImageRepository.findById(pi.getId()).get();
        } else {
            productImage = new ProductImage();
        }
        productImage.setImagePath(pi.getImagePath());
        ProductImage savedProduct = productImageRepository.save(productImage);
        pi.setId(savedProduct.getId());
        return pi;
    }
}