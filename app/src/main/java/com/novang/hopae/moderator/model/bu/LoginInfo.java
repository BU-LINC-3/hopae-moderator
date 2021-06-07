package com.novang.hopae.moderator.model.bu;

import com.google.gson.annotations.SerializedName;

public class LoginInfo {

    @SerializedName("isLogined")
    public boolean isLogined;

    @SerializedName("userId")
    public String userId;

    @SerializedName("univCd")
    public String univCode;

    @SerializedName("userNm")
    public String userName;

    @SerializedName("deptCode")
    public String deptCode;

}
