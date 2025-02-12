package com.ecom.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductDto {
	 String prodName;
     Double prodPrice;
     Integer prodQty;
     String prodDescp;
     Long categoryId; 
//    private MultipartFile imageFile;
     
     public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Double getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(Double prodPrice) {
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
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
//	public MultipartFile getImageFile() {
//        return imageFile;
//    }
//
//    public void setImageFile(MultipartFile imageFile) {
//        this.imageFile = imageFile;
//    }
    
    
    public ProductDto(String prodName, Double prodPrice, Integer prodQty, String prodDescp, Long categoryId) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.prodQty = prodQty;
        this.prodDescp = prodDescp;
        this.categoryId = categoryId;
     
    }
	

	@Override
	public String toString() {
		return "ProductDto [prodName=" + prodName + ", prodPrice=" + prodPrice + ", prodQty=" + prodQty + ", prodDescp="
				+ prodDescp + ", categoryId=" + categoryId + "]";
	}
	
}
