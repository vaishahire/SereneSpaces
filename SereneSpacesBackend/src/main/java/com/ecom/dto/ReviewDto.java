package com.ecom.dto;


public class ReviewDto {

    private Integer count;
    private String comment;
    private Long productId;
    private Long userId;

    public ReviewDto(Integer count, String comment, Long productId, Long userId) {
        this.count = count;
        this.comment = comment;
        this.productId = productId;
        this.userId = userId;
    }

    public ReviewDto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
