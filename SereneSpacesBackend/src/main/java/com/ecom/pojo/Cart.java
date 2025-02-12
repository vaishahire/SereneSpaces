package com.ecom.pojo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    Long cartId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartProduct> cartProducts;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Cart() {
        super();
    }

    public Cart(List<CartProduct> cartProducts, User user) {
        this.cartProducts = cartProducts;
        this.user = user;
    }

    // Getters and Setters
    public Long getCartId() {
        return cartId;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public User getUser () {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cart [cartProducts=" + cartProducts + ", user=" + user + "]";
    }
}