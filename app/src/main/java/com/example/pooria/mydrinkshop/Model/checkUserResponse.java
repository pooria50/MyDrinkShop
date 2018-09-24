package com.example.pooria.mydrinkshop.Model;

public class checkUserResponse {
    private boolean exists;
    private String error_msg;


    public checkUserResponse() {
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
