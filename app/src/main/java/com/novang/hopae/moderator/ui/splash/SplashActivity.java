package com.novang.hopae.moderator.ui.splash;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.novang.hopae.moderator.ui.auth.AuthActivity;
import com.novang.hopae.moderator.ui.base.BaseActivity;
import com.novang.hopae.moderator.ui.main.MainActivity;

import java.util.Objects;

public class SplashActivity extends BaseActivity {

    private SplashViewModel viewModel;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        super.init(savedInstanceState);

        start(this, AuthActivity.class);
        finish();
    }

    @Override
    protected void initReferences() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initObservers() {

    }

    @Override
    protected void initEvents() {

    }
}
