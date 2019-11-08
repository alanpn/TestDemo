package com.wubin.testdemo.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wubin.baselibrary.util.StringUtil;

/**
 * @author wubin
 * @description
 * @date 2019-05-21
 */
public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFromState> loginFromState = new MutableLiveData<>();

    MutableLiveData getLoginFromState() {
        return loginFromState;
    }

    private LoginRepository loginRepository;

    public LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public void login(String txt) {

        if (StringUtil.isBlank(txt)) {

            loginFromState.setValue(new LoginFromState(null, "error"));

        } else {

            Result<UserPo> result = loginRepository.login(txt, "bb");

            if (result instanceof Result.Success) {
                UserPo data = ((Result.Success<UserPo>) result).getData();
                loginFromState.setValue(new LoginFromState(data.getDisplayName() + " success", null));
            } else {
                loginFromState.setValue(new LoginFromState(null, "error"));
            }

        }
    }


}
