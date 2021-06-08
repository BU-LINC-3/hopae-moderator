package com.novang.hopae.moderator.ui.auth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.novang.hopae.moderator.R;
import com.novang.hopae.moderator.model.bu.LoginResponse;
import com.novang.hopae.moderator.ui.base.BaseActivity;
import com.novang.hopae.moderator.ui.main.MainActivity;
import com.novang.hopae.moderator.view.LoadingView;

import java.util.Objects;

public class AuthActivity extends BaseActivity {

    private AuthViewModel viewModel;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    private LoadingView loadingView;
    private MaterialButtonToggleGroup loginFormTypeContainer;
    private EditText loginFromId;
    private EditText loginFromPw;
    private MaterialButton loginButton;

    private int userType = 1;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_auth);
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        super.init(savedInstanceState);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        final int univerGu = sharedPreferences.getInt("univerGu", 0);
        final String userId = sharedPreferences.getString("userId", null);
        final String userPw = sharedPreferences.getString("userPw", null);

        if (!Objects.equals(univerGu, 0) &&!Objects.equals(userId, null) && !Objects.equals(userPw, null)) {
            viewModel.requestLogin(univerGu, userId, userPw);
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initReferences() {
        loadingView = findViewById(R.id.loading_view);
        loginFormTypeContainer = findViewById(R.id.login_form_type_container);
        loginFromId = findViewById(R.id.login_form_id);
        loginFromPw = findViewById(R.id.login_form_pw);
        loginButton = findViewById(R.id.login_button);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initObservers() {
        viewModel.getLogin().observe(this, loginResponse -> {
            if (loginResponse.getState() == LoginResponse.SUCCESS) {
                viewModel.requestLoginInfo();
            } else if (loginResponse.getState() == LoginResponse.CHANGE) {
                loadingView.setVisibility(View.GONE);
                Toast.makeText(this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                loadingView.setVisibility(View.GONE);
                Toast.makeText(this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        viewModel.getLoginInfo().observe(this, loginInfo -> {
            loadingView.setVisibility(View.GONE);

            if (loginInfo.isLogined()) {
                if (!Objects.equals(sharedPreferencesEditor, null)) {
                    sharedPreferencesEditor.apply();
                }
                start(this, MainActivity.class, "LoginInfo", loginInfo);
                finish();
            } else {
                Toast.makeText(this, "세션이 만료되었습니다. 다시 로그인해주세요.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void initEvents() {
        loginFormTypeContainer.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                userType = group.indexOfChild(findViewById(checkedId)) + 1;
            }
        });

        loginButton.setOnClickListener(v -> {
            final String id = loginFromId.getText().toString();
            final String pw = loginFromPw.getText().toString();

            sharedPreferencesEditor = sharedPreferences.edit();
            sharedPreferencesEditor.putInt("univerGu", userType);
            sharedPreferencesEditor.putString("userId", id);
            sharedPreferencesEditor.putString("userPw", pw);

            viewModel.requestLogin(userType, id, pw);
            loadingView.setVisibility(View.VISIBLE);
        });
    }
}