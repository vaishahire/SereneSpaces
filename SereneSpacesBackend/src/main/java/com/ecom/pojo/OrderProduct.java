package com.ecom.pojo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_product")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;


    @Column(name = "selected_qty", nullable = false)
    private Integer selectedQty;

    @Column(name = "prodname", nullable = false)
    private String prodName;

    @Column(name = "img_url", nullable = false,length = 100000)
    private String imgUrl;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "description", nullable = true)
    private String description;

    public OrderProduct() {
        super();
    }

    public OrderProduct(Order order, Integer selectedQty, String prodName, String imgUrl, Double price, String description) {
        this.order = order;
        this.selectedQty = selectedQty;
        this.prodName = prodName;
        this.imgUrl = imgUrl;
        this.price = price;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getSelectedQty() {
        return selectedQty;
    }

    public void setSelectedQty(Integer selectedQty) {
        this.selectedQty = selectedQty;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OrderProduct [id=" + id + ", prodName=" + prodName + ", price=" + price + "]";
    }
}
