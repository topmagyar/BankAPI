package com.dev.bank.models.response;

public class AuthRegisterResponse extends BaseResponse {
    private Integer userId;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }
}
