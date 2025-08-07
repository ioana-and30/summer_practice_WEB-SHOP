package com.createq.curlsie.controller;

import com.createq.curlsie.dto.AnonymousCartItemDTO;
import com.createq.curlsie.dto.CartDTO;
import com.createq.curlsie.dto.CartItemDTO;
import com.createq.curlsie.facades.CartFacade;
import com.createq.curlsie.model.CartItemModel;
import com.createq.curlsie.model.CustomUserDetails;
import com.createq.curlsie.model.ProductModel;
import com.createq.curlsie.model.UserModel;
import com.createq.curlsie.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    private final CartFacade cartFacade;
    private final ProductService productService;

    public CartController(CartFacade cartFacade, ProductService productService) {
        this.cartFacade = cartFacade;
        this.productService = productService;
    }

    @GetMapping("/cart")
    public String showCart(Model model, HttpServletRequest request) {

        Long userId = getCurrentUserId();
        String sessionId = request.getSession().getId();

        model.addAttribute("cart", cartFacade.getCart(userId,sessionId));
        return "cart";
    }

    @GetMapping("/cart/json")
    @ResponseBody
    public ResponseEntity<List<CartItemDTO>> getUserCart(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<CartItemDTO> cart = cartFacade.getUserCart(principal.getName());
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/cart/merge")
    @ResponseBody
    public ResponseEntity<?> mergeCart(@RequestBody List<CartItemDTO> items, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }

        cartFacade.mergeCartItems(principal.getName(), items);
        return ResponseEntity.ok("Cart merged");
    }

    @GetMapping("/cart/count")
    @ResponseBody
    public ResponseEntity<?> getCartCount(HttpServletRequest request) {
        Long userId = getCurrentUserId();
        String sessionId = request.getSession().getId();

        int count = cartFacade.getCartItemCount(userId, sessionId);
        return ResponseEntity.ok(Map.of("count", count));
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addToCart(@RequestBody Map<String, Object> payload,HttpServletRequest request) {

        Long productId = Long.valueOf(payload.get("productId").toString());
        int quantity = Integer.parseInt(payload.get("quantity").toString());

        String sessionId = request.getSession().getId();

        Long userId = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof CustomUserDetails userDetails) {
                userId = userDetails.getUser().getId();
            }
        }

        ProductModel product = productService.getByProductId(productId);
        if (product == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "The product does not exist"));
        }

        int availableStock = product.getQuantity();
        if (quantity <= 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "The quantity must be greater than zero."));
        }

        if (quantity > availableStock) {
            return ResponseEntity.badRequest().body(Map.of("error", "The quantity goes above the available stock."));
        }

        cartFacade.addItemToCart(userId, productId, sessionId, quantity);

        return ResponseEntity.ok(Map.of("message", "Product successfully added to the cart."));
    }


    @PostMapping("/update")
    public String updateItem(@RequestParam Long productId, @RequestParam int quantity, HttpServletRequest request) {

        String sessionId=request.getSession().getId();
        Long userId = getCurrentUserId();

        cartFacade.updateItemQuantity(userId, sessionId,productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeItem(@RequestParam Long productId, HttpServletRequest request) {

        Long userId =getCurrentUserId();
        String sessionId = request.getSession().getId();

        cartFacade.removeItemFromCart(userId,sessionId, productId);
        return "redirect:/cart";
    }

    private UserModel getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof CustomUserDetails) {
                return ((CustomUserDetails) principal).getUser();
            }
        }
        return null;
    }

    private Long getCurrentUserId() {
        UserModel user = getAuthenticatedUser();
        return user != null ? user.getId() : null;
    }
}
