package com.novang.hopae.moderator.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.novang.hopae.moderator.R;
import com.novang.hopae.moderator.model.bu.LoginInfo;
import com.novang.hopae.moderator.ui.base.BaseActivity;
import com.novang.hopae.moderator.ui.pass.PassActivity;

public class MainActivity extends BaseActivity {

    private MainViewModel viewModel;

    private LoginInfo loginInfo;

    private MaterialButton passStartButton;


    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        super.init(savedInstanceState);
        loginInfo = getIntent().getParcelableExtra("LoginInfo");
    }

    @Override
    protected void initReferences() {
        passStartButton = findViewById(R.id.pass_start_button);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initObservers() {

    }

    @Override
    protected void initEvents() {
        passStartButton.setOnClickListener(v -> {
            start(this, PassActivity.class, "ModeratorInfo", loginInfo);
        });
    }
}