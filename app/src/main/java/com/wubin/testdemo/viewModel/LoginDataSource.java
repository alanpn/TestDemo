package com.wubin.testdemo.viewModel;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<UserPo> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            UserPo fakeUser = new UserPo(java.util.UUID.randomUUID().toString(), username);
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
