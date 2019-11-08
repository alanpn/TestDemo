package com.wubin.testdemo.SimpleViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wubin.testdemo.viewModel.UserPo;

import java.util.Arrays;
import java.util.List;

/**
 * @author wubin
 * @description
 * @date 2019-11-05
 */
public class MyViewModel extends ViewModel {

    MutableLiveData<List<UserPo>> users;

    public MutableLiveData<List<UserPo>> getUsers() {
        if (null == users) {
            users = new MutableLiveData<>();
            users.setValue(Arrays.asList(new UserPo("1", "haha"), new UserPo("2", "hehe")));
        }
        return users;

    }
}
