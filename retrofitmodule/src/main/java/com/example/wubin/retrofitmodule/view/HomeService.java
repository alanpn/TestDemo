package com.example.wubin.retrofitmodule.view;

import com.sand.sandcashier.contract.HomeContract;

import java.util.HashMap;
import java.util.Map;

public class HomeService extends BaseService implements HomeContract.Service {

    private static class Holder {
        private static final HomeService storeService = new HomeService();
    }

    private HomeService() {
    }

    public static HomeService getService() {
        return Holder.storeService;
    }

    @Override
    public Map<String, String> setTodaySales() {
        Map<String, String> map = new HashMap<>();
        return signParas(map);
    }

}
