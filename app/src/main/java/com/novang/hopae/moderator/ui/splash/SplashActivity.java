package com.novang.hopae.moderator.ui.splash;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.novang.hopae.moderator.ui.base.BaseActivity;
import com.novang.hopae.moderator.ui.main.MainActivity;

public class SplashActivity extends BaseActivity {

    private SplashViewModel viewModel;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        super.init(savedInstanceState);

        start(this, MainActivity.class);
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
