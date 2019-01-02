package com.example.wubin.retrofitmodule.view;

/**
 * Created by Administrator on 2017/1/16.
 */
public class RxBean {
    public String count;
    public String start;
    public String total;

    public String description;
    public String image_url;

    @Override
    public String toString() {
        return "RxBean{" +
                "count='" + count + '\'' +
                ", start='" + start + '\'' +
                ", total='" + total + '\'' +
                ", description='" + description + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
