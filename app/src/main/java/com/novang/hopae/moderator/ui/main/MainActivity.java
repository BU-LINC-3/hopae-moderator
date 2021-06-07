package com.novang.hopae.moderator.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.novang.hopae.moderator.R;
import com.novang.hopae.moderator.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private MainViewModel viewModel;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        super.init(savedInstanceState);
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