# hopae-moderator
## 호패 관리자 클라이언트

Android 용 호패 관리자 클라이언트입니다.<br/>

SDK: API 24^<br/>
IDE: Android Studio 4.1.2<br/>

## Summary
[Google Slide](https://docs.google.com/presentation/d/1hxOBRRBrWIrjKF4EnZRzaZaGaHRS54hI6fGh_IULV1I/edit?usp=sharing)
<br/>

## QRCode 정보
### 출입증 요청
```
{
    "type": "issue",
    "sessionId": "JSESSIONID=1B6696A515EA84522DBDE609AF455F4C",
    "invitation": {
        "@type": "did:sov:BzCbsTYhMrjHiqZDDUAAHg;spec/connections/1.0/invitation",
        "@id": "d8e869cd-4feb-4233-930f-1c93a35a04c9",
        "serviceEndpoint": "http://172.17.0.1:8030",
        "label": "alice.agent",
        "recipientKeys": [
            "t2f6w56h54PsPeEuMiK4zPZHbM1Jx1fSNMeM4u5SwAM"
        ]
    },
    "alias": null,
    "credRevId": null,
    "revRegId": null
}
```
<br/>

### 출입증 증명
```
{
    "type": "proof",
    "alias": "hopae-20161234-2fa74b01-0f28-427f-bf1c-59889b933ff8",
    "credRevId": 2,
    "revRegId": "DRMia1UXAkiFJX891d24tm:4:DRMiNaUXAkiFJX891H24tm:2:CL:22:revocable:CL_ACCUM:c1466c7a-c8b3-47c0-9332-9f5ace3429db",
    "sessionId": null,
    "invitation": null
}
```

|    Key     |         Type         |                      Description                       | Required |
| :--------: | :------------------: | :----------------------------------------------------: | :------: |
|    type    |        String        |     "issue": Request Pass<br/>"proof": Prove Pass      |   Both   |
| sessionId  |        String        |    Session ID from "/api/did/issuer/create-session"    |  issue   |
| invitation | ConnectionInvitation | Invitation from agent "/connections/create-invitation" |  issue   |
|   alias    |        String        | Unique Agent ID from "/api/did/issuer/create-session"  |  proof   |
| credRevId  |        String        |   Credential Revocation ID from agent "/credentials"   |  proof   |
|  revRegId  |        String        |    Revocation Registry ID from agent "/credentials"    |  proof   |
