package com.createq.curlsie.controller;

import com.createq.curlsie.dto.CategoryDTO;
import com.createq.curlsie.dto.ProductDTO;
import com.createq.curlsie.exceptions.ResourceNotFoundException;
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
        try {
            var categories = categoryFacade.getAll();
            model.addAttribute("categories", categories);

            Long idToUse = (categoryId == null) ? 1L : categoryId;

            CategoryDTO selectedCategory = categoryFacade.getByCategoryId(idToUse);
            model.addAttribute("selectedCategory", selectedCategory);

            var products = productFacade.getByCategoryId(idToUse);
            model.addAttribute("products", products);

        } catch (ResourceNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("products", List.of());
        }

        return "categories";
    }

}
