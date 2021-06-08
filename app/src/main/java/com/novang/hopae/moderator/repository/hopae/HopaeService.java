package com.novang.hopae.moderator.repository.hopae;

import com.novang.hopae.moderator.model.hopae.ConnRecord;
import com.novang.hopae.moderator.model.hopae.ConnectionInvitation;
import com.novang.hopae.moderator.model.hopae.CreateCredentialDefResponse;
import com.novang.hopae.moderator.model.hopae.CreateSchemaResponse;
import com.novang.hopae.moderator.model.hopae.IssueCredentialResponse;
import com.novang.hopae.moderator.model.hopae.RequestProofResponse;
import com.novang.hopae.moderator.model.hopae.RevokeCredentialResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HopaeService {

    @POST("/api/did/issuer/receive-invitation")
    Call<ConnRecord> receiveInvitation(
            @Header("Cookie") String sessionId,
            @Body() ConnectionInvitation body
    );

    @GET("/api/did/issuer/create-schema")
    Call<CreateSchemaResponse> createSchema(
            @Header("Cookie") String sessionId
    );

    @GET("/api/did/issuer/create-credential-def")
    Call<CreateCredentialDefResponse> createCredentialDef(
            @Header("Cookie") String sessionId
    );

    @GET("/api/did/issuer/issue-credential")
    Call<IssueCredentialResponse> issueCredential(
            @Header("Cookie") String sessionId,
            @Query("temp") int temp
    );

    @GET("/api/did/issuer/revoke-credential")
    Call<RevokeCredentialResponse> revokeCredential(
            @Query("credExId") String credExId,
            @Query("credRevId") String credRevId,
            @Query("revRegId") String revRegId
    );

    @GET("/api/did/verifier/prove")
    Call<RequestProofResponse> requestProof(
            @Query("alias") String alias,
            @Query("moderatorId") String moderatorId,
            @Query("location") String location
    );

}
