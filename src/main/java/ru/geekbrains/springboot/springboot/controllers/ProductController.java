package ru.geekbrains.springboot.springboot.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springboot.springboot.models.Product;
import ru.geekbrains.springboot.springboot.models.ProductCategory;
import ru.geekbrains.springboot.springboot.services.ProductCategoriesService;
import ru.geekbrains.springboot.springboot.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductCategoriesService productCategoriesService;

    @GetMapping
    public String showAll(
            Model model,
            @RequestParam(required = false, name = "product_category") Long productCategory
    ) {
        List<Product> out = productService.findAll();
        if (productCategory != null) {
            out = out.stream().filter(p -> p.getPg().getId().equals(productCategory)).collect(Collectors.toList());
        }
        model.addAttribute("products", out);
        model.addAttribute("productCategories", productCategoriesService.findAll());
        return "products";
    }

    @GetMapping("/add")
    public String showProductAddForm(Model model) {
        model.addAttribute("productCategories", productCategoriesService.findAll());
        return "add_product";
    }

    @PostMapping("/add")
    public String addNewProduct(
            @ModelAttribute Product newProduct,
            @RequestParam(required = false, name = "product_category") Long productCategory) {
        ProductCategory productCategory1 = productCategoriesService.findById(productCategory);
        newProduct.setPg(productCategory1);
        productService.insertOrUpdate(newProduct);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showProductEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("productCategories", productCategoriesService.findAll());
        return "edit_product";
    }

    @PostMapping("/edit")
    public String editProduct(
            @ModelAttribute Product editedProduct,
            @RequestParam(required = false, name = "product_category") Long productCategory) {
        ProductCategory productCategory1 = productCategoriesService.findById(productCategory);
        editedProduct.setPg(productCategory1);
        productService.insertOrUpdate(editedProduct);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String removeById(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

}
