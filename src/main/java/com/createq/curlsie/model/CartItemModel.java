package com.createq.curlsie.model;

import jakarta.persistence.*;

@Entity
@Table(name="CartItem",  uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cart_id", "product_id"})
})

public class CartItemModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private CartModel cart;

    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductModel product;

    @Column
    private int quantity;

    @Column
    private double price;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCart(CartModel cart) {
        this.cart = cart;
    }

    public CartModel getCart() {
        return cart;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
