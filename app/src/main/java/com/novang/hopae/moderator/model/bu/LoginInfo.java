package com.novang.hopae.moderator.model.bu;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LoginInfo implements Parcelable {

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

    public LoginInfo() {

    }

    protected LoginInfo(Parcel in) {
        isLogined = in.readByte() != 0;
        userId = in.readString();
        univCode = in.readString();
        userName = in.readString();
        deptCode = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isLogined ? 1 : 0));
        dest.writeString(userId);
        dest.writeString(univCode);
        dest.writeString(userName);
        dest.writeString(deptCode);
    }

    public static final Creator<LoginInfo> CREATOR = new Creator<LoginInfo>() {
        @Override
        public LoginInfo createFromParcel(Parcel in) {
            return new LoginInfo(in);
        }

        @Override
        public LoginInfo[] newArray(int size) {
            return new LoginInfo[size];
        }
    };

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
