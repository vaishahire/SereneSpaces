package com.ecom.dto;

public class UpdateUserProfileDto {


    private String userName;
    private String address;
    private String mobileNumber;

    public UpdateUserProfileDto() {
    }

    public UpdateUserProfileDto(String userName, String address, String mobileNumber) {
        this.userName = userName;
        this.address = address;
        this.mobileNumber = mobileNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
