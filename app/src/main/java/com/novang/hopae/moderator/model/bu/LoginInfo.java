package com.novang.hopae.moderator.model.bu;

import com.google.gson.annotations.SerializedName;

public class LoginInfo {

    @SerializedName("isLogined")
    private boolean isLogined;

    @SerializedName("userId")
    private String userId;

    @SerializedName("univCd")
    private String univCode;

    @SerializedName("userNm")
    private String userName;

    @SerializedName("deptCode")
    private String deptCode;

    public boolean isLogined() {
        return isLogined;
    }

    public void setLogined(boolean logined) {
        isLogined = logined;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUnivCode() {
        return univCode;
    }

    public void setUnivCode(String univCode) {
        this.univCode = univCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
}
