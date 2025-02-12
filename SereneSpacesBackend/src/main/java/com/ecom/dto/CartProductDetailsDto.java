package com.ecom.dto;

public class CartProductDetailsDto {
	private Long productId;
    private String productName;
    private Double productPrice;
    private Integer selectedQty;
    private byte[] prodImage;
    private String descp;
    
	public byte[] getProdImage() {
		return prodImage;
	}
	public void setProdImage(byte[] prodImage) {
		this.prodImage = prodImage;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getSelectedQty() {
		return selectedQty;
	}
	public void setSelectedQty(Integer selectedQty) {
		this.selectedQty = selectedQty;
	}
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	public CartProductDetailsDto(Long productId, String productName, Double productPrice, Integer selectedQty,
			byte[] prodImage, String descp) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.selectedQty = selectedQty;
		this.prodImage = prodImage;
		this.descp = descp;
	}
	
	
	
	
	

    
}