package com.example.wubin.retrofitmodule.view;


import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.retrofitmodule.util.MyCallBack;
import com.example.wubin.retrofitmodule.util.ReFt;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;

    private IHome iHome = ReFt.get().create(IHome.class);
    private HomeService service;

    public HomePresenter() {
        getService();
    }

    private void getService() {
        service = HomeService.getService();
    }

    @Override
    public HomeContract.Presenter setView(HomeContract.View view) {
        this.view = view;
        return this;
    }

    /**
     * 当日交易统计
     */
    @Override
    public void setTodaySales() {

        iHome.getTodaySales("ssss", service.setTodaySales()).enqueue(new MyCallBack<TodaySalesPo>() {
            @Override
            public void onResponse(TodaySalesPo todaySalesPo) {
//                BaseActivity.dismissDialog();
                view.setTodaySales(todaySalesPo);
            }

            @Override
            public void onFailure(Throwable t) {
//                BaseActivity.dismissDialog();
//                BaseActivity.showErrorMessage(t);

                view.setTodaySales(null);
            }
        });

    }
}
