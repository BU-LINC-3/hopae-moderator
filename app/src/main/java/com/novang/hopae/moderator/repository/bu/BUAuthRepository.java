package com.novang.hopae.moderator.repository.bu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.novang.hopae.moderator.model.bu.LoginInfo;

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

    public BUAuthRepository() {
        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient().newBuilder()
                        .build())
                .baseUrl("https://www.bu.ac.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(BUAuthService.class);
    }

    public LiveData<String> requestLogin(int univerGu, String userId, String userPw) {
        MutableLiveData<String> data = new MutableLiveData<>();

        Call<ResponseBody> request = service.requestLogin(univerGu, userId, userPw);
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() != 200 || response.body() == null) {
                    data.postValue("");
                } else {
                    data.postValue(response.raw().header("Cookie"));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return data;
    }

    public LiveData<LoginInfo> requestLoginInfo(String sessionId) {
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