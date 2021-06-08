package com.novang.hopae.moderator.model.bu;

import java.util.List;

import retrofit2.Response;

public class LoginResponse {

    public static final int SUCCESS = 0;
    public static final int MESSAGE = 1;
    public static final int CHANGE = 2;

    private int state;

    private String redirect;

    private String message;

    public LoginResponse(Response<?> response) {
        if (response.raw().request().method().equals("POST")) {
            state = SUCCESS;
        } else {
            List<String> pathSegments = response.raw().request().url().pathSegments();
            if (pathSegments.contains("subview.do")) {
                state = CHANGE;
                redirect = response.raw().request().url().toString();
                message = "로그인 오류:\n홈페이지에서 비밀번호를 변경해주세요.";
            } else {
                state = MESSAGE;
                message = "로그인 오류:\n";
                message += response.raw().request().url().queryParameter("message");
                message = message.replace("<br/>", "\n");
            }
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
