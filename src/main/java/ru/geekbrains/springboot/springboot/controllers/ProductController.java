package ru.geekbrains.springboot.springboot.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springboot.springboot.models.Product;
import ru.geekbrains.springboot.springboot.services.ProductService;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/add")
    public String showProductAddForm() {
        return "add_product";
    }

    @PostMapping("/add")
    public String addNewProduct(@ModelAttribute Product newProduct) {
        productService.insertOrUpdate(newProduct);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showProductEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "edit_product";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute Product editedProduct) {
        productService.insertOrUpdate(editedProduct);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String removeById(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

}
