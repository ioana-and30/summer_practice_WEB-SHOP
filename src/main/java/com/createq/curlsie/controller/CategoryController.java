package com.createq.curlsie.controller;

import com.createq.curlsie.dto.ProductDTO;
import com.createq.curlsie.facades.CategoryFacade;
import com.createq.curlsie.facades.ProductFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
public class CategoryController {

    private final CategoryFacade categoryFacade;
    private final ProductFacade productFacade;

    public CategoryController(CategoryFacade categoryFacade, ProductFacade productFacade) {
        this.categoryFacade = categoryFacade;
        this.productFacade = productFacade;
    }

    @GetMapping("/categories")
    public String getCategories(@RequestParam(value = "categoryId", required = false) Long categoryId, Model model) {
        var categories = categoryFacade.getAll();
        model.addAttribute("categories", categories);

        if (categoryId==null && !categories.isEmpty()) {
            categoryId = categories.get(0).getId();
        }

        if (categoryId != null) {
            model.addAttribute("products", productFacade.getByCategoryId(categoryId));
        }

        model.addAttribute("selectedCategoryId", categoryId);

        return "categories";
    }

    @GetMapping("/category-products")
    @ResponseBody
    public List<ProductDTO> getProductsByCategoryJson(@RequestParam Long categoryId) {
        return productFacade.getByCategoryId(categoryId);
    }
}
