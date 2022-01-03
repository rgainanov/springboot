package ru.geekbrains.springboot.springboot.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springboot.springboot.models.Product;
import ru.geekbrains.springboot.springboot.models.ProductCategory;
import ru.geekbrains.springboot.springboot.services.ProductCategoriesService;
import ru.geekbrains.springboot.springboot.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductCategoriesService productCategoriesService;

    @GetMapping
    public String showAll(
            Model model,
            @RequestParam(required = false, name = "product_category") Long productCategory,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "7") int size
    ) {
        Pageable paging = PageRequest.of(page - 1, size);
        Page<Product> productPage = productService.getPage(paging);
        List<Product> out = productPage.getContent();

        if (productCategory != null) {
            out = out.stream().filter(p -> p.getPg().getId().equals(productCategory)).collect(Collectors.toList());
        }

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("products", out);
        model.addAttribute("productCategories", productCategoriesService.findAll());
        model.addAttribute("productPage", productPage);
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
