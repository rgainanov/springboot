package ru.geekbrains.springboot.springboot.forms;

import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.springboot.springboot.models.ProductCategory;

import java.util.Arrays;

public class NewProductUploadForm {
    private MultipartFile[] files;
    private String title;
    private Double price;
    private Long productCategoryId;

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setProductCategory(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    @Override
    public String toString() {
        return "NewProductUploadForm{" +
                "files=" + Arrays.toString(files) +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", productCategoryId=" + productCategoryId +
                '}';
    }
}
