package com.wubin.testdemo.viewModel;

import androidx.annotation.NonNull;

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

    @NonNull
    @Override
    public String toString() {
        return userId + " " + displayName;
    }
}
