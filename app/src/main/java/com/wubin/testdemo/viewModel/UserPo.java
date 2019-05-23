package com.wubin.testdemo.viewModel;

/**
 * @author wubin
 * @description
 * @date 2019-05-23
 */
public class UserPo {

    private String userId;
    private String displayName;

    public UserPo(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
}
