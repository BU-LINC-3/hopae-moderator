package com.novang.hopae.moderator.repository.bu;

import com.novang.hopae.moderator.model.bu.LoginInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BUAuthService {

    @POST("/subLogin/web/login.do")
    Call<ResponseBody> requestLogin(
            @Query("univerGu") int univerGu,
            @Query("userId") String userId,
            @Query("userPwd") String userPw
    );

    @GET("/restful/checkLogined.do")
    Call<LoginInfo> requestLoginInfo(
            @Header("Cookie") String sessionId
    );

}
