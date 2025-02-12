package com.ecom.dto;

public class FavouriteDto {

    private Long productId;
    private Long userId;

    public FavouriteDto(Long productId, Long userId) {
        this.productId = productId;
        this.userId = userId;
    }

    public FavouriteDto() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
