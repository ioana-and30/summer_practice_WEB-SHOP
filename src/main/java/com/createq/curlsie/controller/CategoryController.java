package com.createq.curlsie.controller;

import com.createq.curlsie.dto.CategoryDTO;
import com.createq.curlsie.exceptions.ResourceNotFoundException;
import com.createq.curlsie.facades.CategoryFacade;
import com.createq.curlsie.facades.ProductFacade;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    private final CategoryFacade categoryFacade;
    private final ProductFacade productFacade;

    public CategoryController(CategoryFacade categoryFacade, ProductFacade productFacade) {
        this.categoryFacade = categoryFacade;
        this.productFacade = productFacade;
    }

    @GetMapping("/home")
    public String getCategories(@RequestParam(value = "categoryId", required = false) Long categoryId,
                                @RequestParam(value = "productId", required = false) Long productId,
                                @RequestParam(value = "sort", required = false) String sort,
                                Model model) {
        try {
            addCategoriesToModel(model);
            
            if (productId != null) {
                addProductDetailsToModel(productId, model);
            }
            else {
                addProductsAndSelectedCategoryToModel(categoryId, sort, model);
            }

        } catch (ResourceNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "index";
    }

    private void addProductsAndSelectedCategoryToModel(Long categoryId, String sort, Model model) {
        Sort sorting = parseSort(sort);
        Long idToUse = (categoryId == null) ? 1L : categoryId;

        var products = productFacade.getByCategoryId(idToUse, sorting);
        model.addAttribute("products", products);

        CategoryDTO selectedCategory = categoryFacade.getByCategoryId(idToUse);
        model.addAttribute("selectedCategory", selectedCategory);
    }

    private Sort parseSort(String sort) {
        if ("asc".equalsIgnoreCase(sort)) {
            return Sort.by(Sort.Direction.ASC, "price");
        } else if ("desc".equalsIgnoreCase(sort)) {
            return Sort.by(Sort.Direction.DESC, "price");
        }
        return Sort.unsorted();
    }

    private void addProductDetailsToModel(Long productId, Model model) {
        var product = productFacade.getByProductId(productId);
        model.addAttribute("product_details", product);
    }

    private void addCategoriesToModel(Model model) {
        var categories = categoryFacade.getAll();
        model.addAttribute("categories", categories);
    }

}
