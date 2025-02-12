package com.ecom.dto;

public class PaymentDto {
    private String payType;
    private double payAmt;
    private Long orderId;
    private Long userId; 

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public double getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(double payAmt) {
        this.payAmt = payAmt;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
