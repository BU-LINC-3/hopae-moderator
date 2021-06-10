package com.novang.hopae.moderator.ui.pass;

import android.location.Location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.novang.hopae.moderator.model.hopae.ConnectionInvitation;
import com.novang.hopae.moderator.model.pass.ReceivedQRData;
import com.novang.hopae.moderator.repository.hopae.HopaeRepository;

import java.util.Objects;

public class PassViewModel extends ViewModel {

    public static class STATUS {
        public static final int PROOF_FALSE = -5;
        public static final int PROOF_TRUE = -4;
        public static final int CRED_REVOKED = -3;
        public static final int CRED_ISSUED = -2;
        public static final int FAILED = -1;
        public static final int NONE = 0;
        public static final int RECEIVE_INVITATION = 1;
        public static final int CREATE_SCHEMA = 2;
        public static final int CREATE_CRED_DEF = 3;
        public static final int CRED_DEF_CREATED = 4;
        public static final int ISSUE_CREDENTIAL = 5;
        public static final int REVOKE_CREDENTIAL = 6;
        public static final int VERIFY_PROOF = 7;
    }

    private final HopaeRepository hopaeRepository;

    private MutableLiveData<Integer> status;

    private ReceivedQRData requiredData;
    private String credExId;

    public PassViewModel() {
        hopaeRepository = new HopaeRepository();

    }

    public void reset() {
        requiredData = null;
        credExId = null;
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
        status.postValue(STATUS.RECEIVE_INVITATION);
        hopaeRepository.receiveInvitation(sessionId, invitation).observeForever(connRecord -> {
            if (connRecord == null) {
                status.postValue(STATUS.FAILED);
                return;
            }
            requiredData.setAlias(connRecord.getAlias());
            createSchema(requiredData.getSessionId());
        });
    }

    private void createSchema(String sessionId) {
        status.postValue(STATUS.CREATE_SCHEMA);
        hopaeRepository.createSchema(sessionId).observeForever(schemaResponse -> {
            if (schemaResponse == null) {
                status.postValue(STATUS.FAILED);
                return;
            }
            createCredentialDef(requiredData.getSessionId());
        });
    }

    private void createCredentialDef(String sessionId) {
        status.postValue(STATUS.CREATE_CRED_DEF);
        hopaeRepository.createCredentialDef(sessionId).observeForever(createCredentialDefResponse -> {
            if (createCredentialDefResponse == null) {
                status.postValue(STATUS.FAILED);
                return;
            }
            status.postValue(STATUS.CRED_DEF_CREATED);
        });
    }

    private void issueCredential(String sessionId, int temp) {
        status.postValue(STATUS.ISSUE_CREDENTIAL);
        hopaeRepository.issueCredential(sessionId, temp).observeForever(issueCredentialResponse -> {
            if (issueCredentialResponse == null || issueCredentialResponse.getCredExId() == null) {
                status.postValue(STATUS.FAILED);
                return;
            }
            credExId = issueCredentialResponse.getCredExId();
            status.postValue(STATUS.CRED_ISSUED);
        });
    }

    private void revokeCredential(String credExId, String credRevId, String revRegId) {
        status.postValue(STATUS.REVOKE_CREDENTIAL);
        hopaeRepository.revokeCredential(credExId, credRevId, revRegId).observeForever(revokeCredentialResponse -> {
            if (Objects.equals(revokeCredentialResponse, null) || !revokeCredentialResponse.isRevoked()) {
                status.postValue(STATUS.FAILED);
            } else {
                status.postValue(STATUS.CRED_REVOKED);
            }
        });
    }

    private void requestProof(String alias, String moderatorId, Location location) {
        status.postValue(STATUS.VERIFY_PROOF);

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

            if (requestProofResponse.isVerified()) {
                status.postValue(STATUS.PROOF_TRUE);
            } else {
                status.postValue(STATUS.PROOF_FALSE);
                revokeCredential(credExId, requiredData.getCredRevId(), requiredData.getRevRegId());
            }
        });
    }

    public LiveData<Integer> getStatus() {
        if (status == null) {
            status = new MutableLiveData<>(STATUS.NONE);
        }
        return status;
    }
}
