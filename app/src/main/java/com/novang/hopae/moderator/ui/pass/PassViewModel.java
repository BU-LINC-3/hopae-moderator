package com.novang.hopae.moderator.ui.pass;

import android.location.Location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.novang.hopae.moderator.model.hopae.ConnectionInvitation;
import com.novang.hopae.moderator.model.pass.ReceivedQRData;
import com.novang.hopae.moderator.repository.hopae.HopaeRepository;

public class PassViewModel extends ViewModel {

    public static class STATUS {
        public static final int FAILED = -1;
        public static final int NONE = 0;
        public static final int INV_RECEIVED = 1;
        public static final int SCHEMA_READY = 2;
        public static final int CRED_DEF_READY = 3;
        public static final int CRED_READY = 4;
        public static final int CRED_REVOKED = 5;
        public static final int PROOF_READY = 6;
        public static final int PROOF_FALSE = 7;
    }

    private final HopaeRepository hopaeRepository;

    private MutableLiveData<Integer> status;

    private ReceivedQRData requiredData;
    private String credExId;

    public PassViewModel() {
        hopaeRepository = new HopaeRepository();

    }

    public void prepare(ReceivedQRData receivedQRData) {
        this.requiredData = receivedQRData;
        receiveInvitation(receivedQRData.getSessionId(), receivedQRData.getInvitation());
    }

    public void issue(double temp) {
        issueCredential(requiredData.getSessionId(), (int)(temp * 10));
    }

    public void proof(ReceivedQRData receivedQRData, String moderatorId, Location location) {
        requiredData = receivedQRData;
        requestProof(requiredData.getAlias(), moderatorId, location);
    }

    public void proof(String moderatorId, Location location) {
        requestProof(requiredData.getAlias(), moderatorId, location);
    }

    private void receiveInvitation(String sessionId, ConnectionInvitation invitation) {
        hopaeRepository.receiveInvitation(sessionId, invitation).observeForever(connRecord -> {
            if (connRecord == null) {
                status.postValue(STATUS.FAILED);
                return;
            }
            status.postValue(STATUS.INV_RECEIVED);
            requiredData.setAlias(connRecord.getAlias());
            createSchema(requiredData.getSessionId());
        });
    }

    private void createSchema(String sessionId) {
        hopaeRepository.createSchema(sessionId).observeForever(schemaResponse -> {
            if (schemaResponse == null) {
                status.postValue(STATUS.FAILED);
                return;
            }
            status.postValue(STATUS.SCHEMA_READY);
            createCredentialDef(requiredData.getSessionId());
        });
    }

    private void createCredentialDef(String sessionId) {
        hopaeRepository.createCredentialDef(sessionId).observeForever(createCredentialDefResponse -> {
            if (createCredentialDefResponse == null) {
                status.postValue(STATUS.FAILED);
                return;
            }
            status.postValue(STATUS.CRED_DEF_READY);
        });
    }

    private void issueCredential(String sessionId, int temp) {
        hopaeRepository.issueCredential(sessionId, temp).observeForever(issueCredentialResponse -> {
            if (issueCredentialResponse == null || issueCredentialResponse.getCredExId() == null) {
                status.postValue(STATUS.FAILED);
                return;
            }
            status.postValue(STATUS.CRED_READY);
            credExId = issueCredentialResponse.getCredExId();
        });
    }

    private void revokeCredential(String credExId, String credRevId, String revRegId) {
        hopaeRepository.revokeCredential(credExId, credRevId, revRegId).observeForever(revokeCredentialResponse -> {
            if (revokeCredentialResponse == null) {
                status.postValue(STATUS.FAILED);
                return;
            }
            status.postValue(STATUS.CRED_REVOKED);
        });
    }

    private void requestProof(String alias, String moderatorId, Location location) {
        String locationString = "";
        if (location != null) {
            locationString = String.valueOf(location.getLatitude()).concat(" ")
                    .concat(String.valueOf(location.getLongitude())).concat(" ")
                    .concat(String.valueOf(location.getAltitude()));
        }

        hopaeRepository.requestProof(alias, moderatorId, locationString).observeForever(requestProofResponse -> {
            if (requestProofResponse == null) {
                status.postValue(STATUS.FAILED);
                return;
            }

            if (!requestProofResponse.isVerified()) {
                status.postValue(STATUS.PROOF_FALSE);
                revokeCredential(credExId, requiredData.getCredRevId(), requiredData.getRevRegId());
            }
            status.postValue(STATUS.PROOF_READY);
        });
    }

    public LiveData<Integer> getStatus() {
        if (status == null) {
            status = new MutableLiveData<>(STATUS.NONE);
        }
        return status;
    }
}
