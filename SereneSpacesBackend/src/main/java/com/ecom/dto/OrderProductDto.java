package com.ecom.dto;

public class OrderProductDto {
    private String prodName;
    private String img;
    private String description;
    private double price;
    private int quantity;
    private Long userId;

    // Getters and Setters
    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

	public OrderProductDto(String prodName, String img, String description, double price, int quantity, Long userId) {
		super();
		this.prodName = prodName;
		this.img = img;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.userId = userId;
	}
    
    
}
