package com.novang.hopae.moderator.repository.bu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.novang.hopae.moderator.model.bu.LoginInfo;
import com.novang.hopae.moderator.model.bu.LoginResponse;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BUAuthRepository {

    private Retrofit retrofit;
    private BUAuthService service;

    private String sessionId;

    public BUAuthRepository() {
        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient().newBuilder()
                        .build())
                .baseUrl("https://www.bu.ac.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(BUAuthService.class);
    }

    public LiveData<LoginResponse> requestLogin(int univerGu, String userId, String userPw) {
        MutableLiveData<LoginResponse> data = new MutableLiveData<>();

        Call<ResponseBody> request = service.requestLogin(univerGu, userId, userPw);
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    sessionId = response.raw().header("Set-Cookie");
                    data.postValue(new LoginResponse(response));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                data.postValue(null);
                t.printStackTrace();
            }
        });

        return data;
    }

    public LiveData<LoginInfo> requestLoginInfo() {
        MutableLiveData<LoginInfo> data = new MutableLiveData<>();

        Call<LoginInfo> request = service.requestLoginInfo(sessionId);
        request.enqueue(new Callback<LoginInfo>() {
            @Override
            public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                if (response.code() != 200 || response.body() == null) {
                    data.postValue(new LoginInfo());
                } else {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginInfo> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return data;
    }

}