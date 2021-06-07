package com.novang.hopae.moderator.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.novang.hopae.moderator.model.bu.LoginInfo;
import com.novang.hopae.moderator.model.bu.LoginResponse;
import com.novang.hopae.moderator.repository.bu.BUAuthRepository;

public class AuthViewModel extends ViewModel {

    private final BUAuthRepository buAuthRepository;

    private MutableLiveData<LoginResponse> login;
    private MutableLiveData<LoginInfo> loginInfo;

    public AuthViewModel() {
        buAuthRepository = new BUAuthRepository();
    }

    public void requestLogin(int univerGu, String userId, String userPw) {
        buAuthRepository.requestLogin(univerGu, userId, userPw).observeForever(loginResponse -> {
            login.postValue(loginResponse);
        });
    }

    public void requestLoginInfo() {
        buAuthRepository.requestLoginInfo().observeForever(info -> {
            loginInfo.postValue(info);
        });
    }

    public LiveData<LoginResponse> getLogin() {
        if (login == null) {
            login = new MutableLiveData<>();
        }
        return login;
    }

    public LiveData<LoginInfo> getLoginInfo() {
        if (loginInfo == null) {
            loginInfo = new MutableLiveData<>();
        }
        return loginInfo;
    }

}
