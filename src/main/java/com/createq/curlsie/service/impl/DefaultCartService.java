package com.createq.curlsie.service.impl;

import com.createq.curlsie.converter.CartConverter;
import com.createq.curlsie.converter.CartItemConverter;
import com.createq.curlsie.dto.CartDTO;
import com.createq.curlsie.dto.CartItemDTO;
import com.createq.curlsie.dto.UserDTO;
import com.createq.curlsie.exceptions.ResourceNotFoundException;
import com.createq.curlsie.model.CartItemModel;
import com.createq.curlsie.model.CartModel;
import com.createq.curlsie.model.ProductModel;
import com.createq.curlsie.model.UserModel;
import com.createq.curlsie.repository.CartItemRepository;
import com.createq.curlsie.repository.CartRepository;
import com.createq.curlsie.repository.ProductRepository;
import com.createq.curlsie.repository.UserRepository;
import com.createq.curlsie.service.CartService;
import com.createq.curlsie.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class DefaultCartService implements CartService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultCartService.class);

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final UserService userService;
    private final CartConverter cartConverter;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public DefaultCartService(CartItemRepository cartItemRepository, CartRepository cartRepository, UserService userService,
                              CartConverter cartConverter, ProductRepository productRepository, UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.cartConverter = cartConverter;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CartDTO getCartByUser(Long userId) {
        CartModel cart = findOrCreateCart(userId, null);
        return cartConverter.convertToDTO(cart);
    }

    @Override
    public CartDTO getCart(Long userId, String sessionId) {
        CartModel cart = findOrCreateCart(userId, sessionId);
        return cartConverter.convertToDTO(cart);
    }

    @Override
    public CartDTO addItemToCart(Long userId, Long productId, String sessionId, int quantity) {
        CartModel cart = findOrCreateCart(userId, sessionId);

        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        int availableStock = product.getQuantity();
        if (quantity <= 0) {
            throw new IllegalArgumentException("Cantitatea trebuie să fie mai mare decât zero");
        }
        if (quantity > availableStock) {
            throw new IllegalArgumentException("Cantitatea depășește stocul disponibil");
        }

        CartItemModel existingItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId)
                .orElse(null);

        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() + quantity;
            if (newQuantity > availableStock) {
                newQuantity = availableStock;
            }
            existingItem.setQuantity(newQuantity);
            existingItem.setPrice(product.getPrice());
            cartItemRepository.save(existingItem);
        } else {
            CartItemModel newItem = new CartItemModel();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setPrice(product.getPrice());
            cartItemRepository.save(newItem);
            cart.getItems().add(newItem);
        }

        recalculateTotal(cart);
        cartRepository.save(cart);

        return cartConverter.convertToDTO(cart);
    }

    @Override
    public CartDTO updateItemQuantity(Long userId, String sessionId, Long productId, int quantity) {
        CartModel cart = findOrCreateCart(userId, sessionId);

        CartItemModel item = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        int availableStock = item.getProduct().getQuantity();
        if (quantity <= 0) {
            throw new IllegalArgumentException("Cantitatea trebuie să fie mai mare decât zero");
        }
        if (quantity > availableStock) {
            throw new IllegalArgumentException("Cantitatea depășește stocul disponibil");
        }

        item.setQuantity(quantity);
        item.setPrice(item.getProduct().getPrice());

        recalculateTotal(cart);
        cartRepository.save(cart);

        return cartConverter.convertToDTO(cart);
    }

    @Override
    public CartDTO removeItemFromCart(Long userId, String sessionId, Long productId) {
        CartModel cart = findOrCreateCart(userId, sessionId);

        cart.getItems().removeIf(i -> i.getProduct().getId().equals(productId));

        recalculateTotal(cart);
        cartRepository.save(cart);

        return cartConverter.convertToDTO(cart);
    }

    @Override
    public int getCartItemCount(Long userId, String sessionId) {
        CartDTO cart = getCart(userId, sessionId);
        if (cart == null) {
            return 0;
        }
        return cart.getItems().stream()
                .mapToInt(CartItemDTO::getQuantity)
                .sum();
    }

    @Override
    public void mergeCartItems(String username, List<CartItemDTO> items) {
        Optional<UserModel> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) return;

        UserModel user = optionalUser.get();

        CartModel cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    CartModel newCart = new CartModel();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        List<CartItemModel> existingItems = cartItemRepository.findByCart(cart);

        Map<Long, CartItemModel> itemMap = existingItems.stream()
                .collect(Collectors.toMap(ci -> ci.getProduct().getId(), ci -> ci));

        for (CartItemDTO dto : items) {
            ProductModel product = productRepository.findById(dto.getId()).orElse(null);
            if (product == null) continue;

            CartItemModel cartItem = itemMap.get(product.getId());

            if (cartItem != null) {
                cartItem.setQuantity(cartItem.getQuantity() + dto.getQuantity());
            } else {
                CartItemModel newItem = new CartItemModel();
                newItem.setCart(cart);
                newItem.setProduct(product);
                newItem.setQuantity(dto.getQuantity());
                newItem.setPrice(product.getPrice()); // opțional
                cartItemRepository.save(newItem);
            }
        }
    }

    @Override
    public List<CartItemDTO> getUserCart(String username) {
        Optional<UserModel> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            return Collections.emptyList();
        }

        UserModel user = optionalUser.get();

        Optional<CartModel> optionalCart = cartRepository.findByUser(user);
        if (optionalCart.isEmpty()) {
            return Collections.emptyList();
        }

        CartModel cart = optionalCart.get();

        return cart.getItems().stream()
                .map(item -> new CartItemDTO(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getPrice(),
                        item.getQuantity()
                ))
                .collect(Collectors.toList());
    }

    private void recalculateTotal(CartModel cart) {
        double total = cart.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        cart.setTotal(total);
    }

    private CartModel findOrCreateCart(Long userId, String sessionId) {
        CartModel cart = null;

        if (userId != null) {
            cart = cartRepository.findByUserId(userId).orElse(null);
            if (cart == null) {
                cart = new CartModel();
                cart.setUser(userService.getUserById(userId));
                cart.setOrderDate(LocalDateTime.now());
                cart.setTotal(0);
                cart.setItems(new ArrayList<>());
                cart = cartRepository.save(cart);
            }
        } else if (sessionId != null && !sessionId.isEmpty()) {
            cart = cartRepository.findBySessionId(sessionId).orElse(null);
            if (cart == null) {
                cart = new CartModel();
                cart.setSessionId(sessionId);
                cart.setOrderDate(LocalDateTime.now());
                cart.setTotal(0);
                cart.setItems(new ArrayList<>());
                cart = cartRepository.save(cart);
            }
        }

        if (cart == null) {
            throw new ResourceNotFoundException("Cart not found");
        }

        return cart;
    }
}