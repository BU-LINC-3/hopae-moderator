package com.novang.hopae.moderator.model.bu;

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
            if (response.raw().request().url().pathSegments().get(2).equals("subview.do")) {
                state = CHANGE;
                redirect = response.raw().request().url().toString();
            } else {
                state = MESSAGE;
                message = response.raw().request().url().queryParameter("message");
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
