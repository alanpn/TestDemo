package com.wubin.testdemo.viewModel;

/**
 * @author wubin
 * @description
 * @date 2019-05-21
 */
public class LoginFromState {

    private String error;
    private String success;

    public LoginFromState(String success, String error) {
        this.success = success;
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
