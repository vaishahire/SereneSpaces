package com.ecom.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_product")
public class CartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "selected_qty", nullable = false)
    private Integer selectedQty;

    public CartProduct() {
        super();
    }

    public CartProduct(Cart cart, Product product, Integer selectedQty) {
        this.cart = cart;
        this.product = product;
        this.selectedQty = selectedQty;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getSelectedQty() {
        return selectedQty;
    }

    public void setSelectedQty(Integer selectedQty) {
        this.selectedQty = selectedQty;
    }
}