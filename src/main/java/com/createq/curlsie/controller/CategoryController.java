package com.createq.curlsie.controller;

import com.createq.curlsie.dto.CategoryDTO;
import com.createq.curlsie.dto.ProductDTO;
import com.createq.curlsie.exceptions.ResourceNotFoundException;
import com.createq.curlsie.facades.CategoryFacade;
import com.createq.curlsie.facades.ProductFacade;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import com.createq.curlsie.utils.Utils;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryFacade categoryFacade;
    private final ProductFacade productFacade;

    public CategoryController(CategoryFacade categoryFacade, ProductFacade productFacade) {
        this.categoryFacade = categoryFacade;
        this.productFacade = productFacade;
    }

    /**
     * Ruta pentru listarea tuturor categoriilor
     * GET /categories
     */
    @GetMapping
    public String getAllCategories(Model model) {
        addCategoriesToModel(model);
        return "index";
    }

    /**
     * Ruta pentru listarea produselor dintr-o anumită categorie
     * GET /categories/category/{categoryId}
     */
    @GetMapping("/category/{categoryId}")
    public String getProductsByCategory(@PathVariable Long categoryId,
                                        @RequestParam(value = "sort", required = false) String sort,
                                        Model model) {
        try {
            addCategoriesToModel(model);
            addProductsAndSelectedCategoryToModel(categoryId, sort, model);
        } catch (ResourceNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "products";
    }

    /**
     * Ruta pentru un produs specific (poate afișa detalii sau redirect)
     * GET /categories/product/{productId}
     */
    @GetMapping("/product_details/{productId}")
    public String getProductDetails(@PathVariable Long productId, Model model) {
        try {
            addCategoriesToModel(model);
            ProductDTO product = productFacade.getByProductId(productId);
            model.addAttribute("product", product);
        } catch (ResourceNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "product_details";
    }

    @GetMapping("/sort")
    public String sortProducts(@RequestParam(value = "categoryId", required = false) Long categoryId,
                               @RequestParam(value = "field", required = false) String field,
                               Model model) {
        try {
            addCategoriesToModel(model);
            addProductsAndSelectedCategoryToModel(categoryId, field, model);
        } catch (ResourceNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index";
    }

    private void addProductsAndSelectedCategoryToModel(Long categoryId, String sort, Model model) {
        Sort sorting = Utils.parseSort(sort);
        Long idToUse = (categoryId == null) ? 1L : categoryId;

        List<ProductDTO> products = productFacade.getByCategoryId(idToUse, sorting);
        model.addAttribute("products", products);

        CategoryDTO selectedCategory = categoryFacade.getByCategoryId(idToUse);
        model.addAttribute("selectedCategory", selectedCategory);
    }

    private void addCategoriesToModel(Model model) {
        var categories = categoryFacade.getAll();
        model.addAttribute("categories", categories);
    }

}
