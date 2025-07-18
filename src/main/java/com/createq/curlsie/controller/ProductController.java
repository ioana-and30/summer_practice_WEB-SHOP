package com.createq.curlsie.controller;

import com.createq.curlsie.exceptions.ResourceNotFoundException;
import com.createq.curlsie.facades.ProductFacade;
import com.createq.curlsie.utils.Utils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductController {

    private final ProductFacade productFacade;

    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping("/products/byCategory")
    public String productsByCategory(@RequestParam Long categoryId,
                                     @RequestParam(value = "sort", required = false) String sort,
                                     Model model) {
        try {
            Sort sorting = Utils.parseSort(sort);

            var products = productFacade.getByCategoryId(categoryId, sorting);
            model.addAttribute("products", products);

        } catch (ResourceNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "products";
    }

    @GetMapping("/product_details")
    public String productDetails(@RequestParam Long productId, Model model) {
        try{
            var product = productFacade.getByProductId(productId);
            model.addAttribute("product_details", product);
        }catch(ResourceNotFoundException e){
            model.addAttribute("error", e.getMessage());
        }
        return "product_details";
    }

    @GetMapping("/cart")
    public String showCart() {
        return "cart";
    }
}
