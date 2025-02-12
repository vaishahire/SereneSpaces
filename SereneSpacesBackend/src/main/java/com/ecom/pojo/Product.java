package com.ecom.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    Long prodId;

    @Column(name = "product_name", length = 50, nullable = false)
    String prodName;

    @Column(name = "price", nullable = false)
    double prodPrice;

    @Column(name = "quantity", nullable = false)
    Integer prodQty;

    @Column(name = "description", length = 100)
    String prodDescp;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @Lob
    @Column(name = "product_image", columnDefinition = "BLOB")
    private byte[] prodImage;

 

    public Product(String prodName, double prodPrice, Integer prodQty, String prodDescp, Category category) {
		super();
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.prodQty = prodQty;
		this.prodDescp = prodDescp;
		this.category = category;
		
	}

	public Product() {
        super();
    }

    // Getters and Setters
    public Long getProdId() {
        return prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(double prodPrice) {
        this.prodPrice = prodPrice;
    }

    public Integer getProdQty() {
        return prodQty;
    }

    public void setProdQty(Integer prodQty) {
        this.prodQty = prodQty;
    }

    public String getProdDescp() {
        return prodDescp;
    }

    public void setProdDescp(String prodDescp) {
        this.prodDescp = prodDescp;
    }

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public byte[] getProdImage() {
		return prodImage;
	}

	public void setProdImage(byte[] prodImage) {
		this.prodImage = prodImage;
	}

	
	
    
}
