package com.novang.hopae.moderator.ui.pass;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
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
import com.google.gson.JsonSyntaxException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.novang.hopae.moderator.R;
import com.novang.hopae.moderator.model.bu.LoginInfo;
import com.novang.hopae.moderator.model.pass.ReceivedQRData;
import com.novang.hopae.moderator.ui.base.BaseActivity;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

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
            if (!Objects.equals(data, null)) {
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
            start(this, PassActivity.class, "ModeratorInfo", moderatorInfo);
            finish();
        });

        viewModel.getStatus().observe(this, status -> {
            switch (status) {
                case PassViewModel.STATUS.RECEIVE_INVITATION :
                    progressStatus.setText("출입증 발급 준비중...");
                    break;
                case PassViewModel.STATUS.CREATE_CRED_DEF :
                    progressStatus.setVisibility(View.GONE);
                    passFormContainer.setVisibility(View.VISIBLE);
                    break;
                case PassViewModel.STATUS.ISSUE_CREDENTIAL :
                    progressStatus.setText("출입증 발급중...");
                    break;
                case PassViewModel.STATUS.CRED_ISSUED :
                    progressStatus.setText("출입증 발급 완료");
                    viewModel.proof(moderatorInfo.getUserId(), null);
                    break;
                case PassViewModel.STATUS.VERIFY_PROOF :
                    progressStatus.setText("출입증 확인중...");
                    break;
                case PassViewModel.STATUS.REVOKE_CREDENTIAL :
                    progressStatus.setText("유효하지 않는 출입증 폐기중...");
                    break;
                case PassViewModel.STATUS.CRED_REVOKED :
                    progressStatus.setText("유효하지 않는 출입증 폐기 완료\n출입증을 새로 발급해주세요.");
                    delayedRelaunch(this, 4000);
                    break;
                case PassViewModel.STATUS.PROOF_TRUE :
                    progressStatus.setText("출입증이 확인되었습니다.");
                    MediaPlayer.create(this, R.raw.confirmed).start();
                    delayedRelaunch(this, 2000);
                    break;
                case PassViewModel.STATUS.PROOF_FALSE :
                    progressStatus.setText("유효하지 않는 출입증입니다.");
                    MediaPlayer.create(this, R.raw.invalid_cred).start();
                case PassViewModel.STATUS.FAILED :
                    progressStatus.setText("요청 실패");
                    MediaPlayer.create(this, R.raw.request_failed).start();
                    delayedRelaunch(this, 2000);
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

            if (!Objects.equals(intentResult, null)) {
                if (!Objects.equals(intentResult.getContents(), null)) {
                    ReceivedQRData qrData = null;
                    try { // TODO: JWT
                        qrData = new GsonBuilder().create()
                                .fromJson(intentResult.getContents(), ReceivedQRData.class);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                    if (!Objects.equals(qrData, null) && qrData.isValidForm()) {
                        receivedQRData.postValue(qrData);
                        return;
                    } else {
                        Toast.makeText(this, "유효하지 않는 QR 코드입니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            intentIntegrator.initiateScan();
        } else if (resultCode == RESULT_CANCELED && requestCode == IntentIntegrator.REQUEST_CODE) {
            finish();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void delayedRelaunch(Activity activity, long time) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                start(activity, PassActivity.class, "ModeratorInfo", moderatorInfo);
                finish();
            }
        }, time);
    }
}