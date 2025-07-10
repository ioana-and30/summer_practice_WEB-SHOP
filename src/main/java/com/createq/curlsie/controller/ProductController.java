package com.createq.curlsie.controller;

import com.createq.curlsie.exceptions.ResourceNotFoundException;
import com.createq.curlsie.facades.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String productsByCategory(@RequestParam Long categoryId, Model model) {

        try{
            var products = productFacade.getByCategoryId(categoryId);
            model.addAttribute("products", products);
        }catch(ResourceNotFoundException e){
            model.addAttribute("error", e.getMessage());
        }
        return "products";
    }
}
