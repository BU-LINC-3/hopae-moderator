package com.novang.hopae.moderator.ui.pass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.gson.GsonBuilder;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.novang.hopae.moderator.R;
import com.novang.hopae.moderator.model.bu.LoginInfo;
import com.novang.hopae.moderator.model.pass.ReceivedQRData;
import com.novang.hopae.moderator.ui.base.BaseActivity;

public class PassActivity extends BaseActivity {

    private PassViewModel viewModel;
    private IntentIntegrator intentIntegrator;

    private ConstraintLayout passFormContainer;
    private TextView progressStatus;
    private EditText passFormTemp;
    private MaterialButton passFormButton;
    private MaterialButton passCancelButton;

    private LoginInfo moderatorInfo;
    private MutableLiveData<ReceivedQRData> receivedQRData;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_pass);
        viewModel = new ViewModelProvider(this).get(PassViewModel.class);
        moderatorInfo = getIntent().getExtras().getParcelable("ModeratorInfo");
        receivedQRData = new MutableLiveData<>();
        super.init(savedInstanceState);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void initReferences() {
        intentIntegrator = new IntentIntegrator(this);
        passFormContainer = findViewById(R.id.pass_form_container);
        progressStatus = findViewById(R.id.progress_status);
        passFormTemp = findViewById(R.id.pass_form_temp);
        passFormButton = findViewById(R.id.pass_form_button);
        passCancelButton = findViewById(R.id.pass_cancel_button);
    }

    @Override
    protected void initViews() {
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        intentIntegrator.setPrompt("QR 코드를 인식시켜주세요.");
        intentIntegrator.setCameraId(1);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setCaptureActivity(CaptureActivity.class);
        passFormContainer.setVisibility(View.GONE);
    }

    @Override
    protected void initObservers() {
        receivedQRData.observe(this, data -> {
            if (data != null) {
                if (data.getType().equals(ReceivedQRData.ISSUE)) {
                    progressStatus.setText("출입증 발급 준비중...");
                    viewModel.prepare(data);
                    return;
                } else if (data.getType().equals(ReceivedQRData.PROOF)){
                    progressStatus.setText("출입증 확인 준비중...");
                    viewModel.proof(data, moderatorInfo.getUserId(), null);
                    return;
                }
            }
            Toast.makeText(this, "QR 코드 인식 오류\nQR 코드를 다시 인식시켜주세요.", Toast.LENGTH_LONG).show();
            start(this, PassActivity.class);
            finish();
        });

        viewModel.getStatus().observe(this, status -> {
            switch (status) {
                case PassViewModel.STATUS.CRED_DEF_READY :
                    progressStatus.setVisibility(View.GONE);
                    passFormContainer.setVisibility(View.VISIBLE);
                    progressStatus.setText("출입증 발급중...");
                    break;
                case PassViewModel.STATUS.CRED_READY :
                    progressStatus.setText("출입증 확인중...");
                    viewModel.proof(moderatorInfo.getUserId(), null);
                    break;
                case PassViewModel.STATUS.CRED_REVOKED :
                    progressStatus.setText("출입증 폐기 완료");
                    break;
                case PassViewModel.STATUS.PROOF_FALSE :
                    progressStatus.setText("유효하지 않는 출입증");
                    break;
                case PassViewModel.STATUS.PROOF_READY :
                    progressStatus.setText("출입증 확인 완료");
                    break;
            }
        });
    }

    @Override
    protected void initEvents() {
        passFormButton.setOnClickListener(v -> {
            passFormContainer.setVisibility(View.GONE);
            progressStatus.setVisibility(View.VISIBLE);
            viewModel.issue(Double.parseDouble(passFormTemp.getText().toString()));
        });

        passCancelButton.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

            if (intentResult != null) {
                if (intentResult.getContents() != null) {
                    receivedQRData.postValue(new GsonBuilder().create()
                            .fromJson(intentResult.getContents(), ReceivedQRData.class));
                }
            }
        } else if (resultCode == RESULT_CANCELED && requestCode == IntentIntegrator.REQUEST_CODE) {
            finish();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}