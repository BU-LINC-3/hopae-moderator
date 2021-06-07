package com.novang.hopae.moderator.repository.hopae;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.novang.hopae.moderator.model.hopae.ConnRecord;
import com.novang.hopae.moderator.model.hopae.ConnectionInvitation;
import com.novang.hopae.moderator.model.hopae.CreateCredentialDefResponse;
import com.novang.hopae.moderator.model.hopae.CreateSchemaResponse;
import com.novang.hopae.moderator.model.hopae.IssueCredentialResponse;
import com.novang.hopae.moderator.model.hopae.RequestProofResponse;
import com.novang.hopae.moderator.model.hopae.RevokeCredentialResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HopaeRepository {

    private Retrofit retrofit;
    private HopaeService service;

    public HopaeRepository() {
        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient().newBuilder()
                        .build())
                .baseUrl("http://211.253.228.16:1060")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(HopaeService.class);
    }

    public LiveData<ConnRecord> receiveInvitation(String sessionId, ConnectionInvitation invitation) {
        MutableLiveData<ConnRecord> data = new MutableLiveData<>();

        Call<ConnRecord> request = service.receiveInvitation(sessionId, invitation);

        request.enqueue(new Callback<ConnRecord>() {
            @Override
            public void onResponse(Call<ConnRecord> call, Response<ConnRecord> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ConnRecord> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return data;
    }

    public LiveData<CreateSchemaResponse> createSchema(String sessionId) {
        MutableLiveData<CreateSchemaResponse> data = new MutableLiveData<>();

        Call<CreateSchemaResponse> request = service.createSchema(sessionId);

        request.enqueue(new Callback<CreateSchemaResponse>() {
            @Override
            public void onResponse(Call<CreateSchemaResponse> call, Response<CreateSchemaResponse> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<CreateSchemaResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return data;
    }

    public LiveData<CreateCredentialDefResponse> createCredentialDef(String sessionId) {
        MutableLiveData<CreateCredentialDefResponse> data = new MutableLiveData<>();

        Call<CreateCredentialDefResponse> request = service.createCredentialDef(sessionId);

        request.enqueue(new Callback<CreateCredentialDefResponse>() {
            @Override
            public void onResponse(Call<CreateCredentialDefResponse> call, Response<CreateCredentialDefResponse> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<CreateCredentialDefResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return data;
    }

    public LiveData<IssueCredentialResponse> issueCredential(String sessionId, int temp) {
        MutableLiveData<IssueCredentialResponse> data = new MutableLiveData<>();

        Call<IssueCredentialResponse> request = service.issueCredential(sessionId, temp);

        request.enqueue(new Callback<IssueCredentialResponse>() {
            @Override
            public void onResponse(Call<IssueCredentialResponse> call, Response<IssueCredentialResponse> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<IssueCredentialResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return data;
    }

    public LiveData<RevokeCredentialResponse> revokeCredential(String credRevId, String revRegId) {
        MutableLiveData<RevokeCredentialResponse> data = new MutableLiveData<>();

        Call<RevokeCredentialResponse> request = service.revokeCredential(credRevId, revRegId);

        request.enqueue(new Callback<RevokeCredentialResponse>() {
            @Override
            public void onResponse(Call<RevokeCredentialResponse> call, Response<RevokeCredentialResponse> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<RevokeCredentialResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return data;
    }

    public LiveData<RequestProofResponse> requestProof(String alias, String moderatorId, String location) {
        MutableLiveData<RequestProofResponse> data = new MutableLiveData<>();

        Call<RequestProofResponse> request = service.requestProof(alias, moderatorId, location);

        request.enqueue(new Callback<RequestProofResponse>() {
            @Override
            public void onResponse(Call<RequestProofResponse> call, Response<RequestProofResponse> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<RequestProofResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return data;
    }

}
