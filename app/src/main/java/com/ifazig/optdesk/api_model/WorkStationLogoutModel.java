package com.ifazig.optdesk.api_model;

public class WorkStationLogoutModel {
    Integer UserId;
    Integer IsLogin;

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public Integer getIsLogin() {
        return IsLogin;
    }

    public void setIsLogin(Integer isLogin) {
        IsLogin = isLogin;
    }
}
