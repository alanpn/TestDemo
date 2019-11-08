package com.example.wubin.retrofitmodule.view;

public class TodaySalesPo {

    private String total_amount; // 总订单金额
    private String total_num; // 总订单数量
    private String success_amount; // 成功交易金额
    private String success_num; // 成功交易笔数
    private String profit_amount; // 收益金额

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getTotal_num() {
        return total_num;
    }

    public void setTotal_num(String total_num) {
        this.total_num = total_num;
    }

    public String getSuccess_amount() {
        return success_amount;
    }

    public void setSuccess_amount(String success_amount) {
        this.success_amount = success_amount;
    }

    public String getSuccess_num() {
        return success_num;
    }

    public void setSuccess_num(String success_num) {
        this.success_num = success_num;
    }

    public String getProfit_amount() {
        return profit_amount;
    }

    public void setProfit_amount(String profit_amount) {
        this.profit_amount = profit_amount;
    }

    @Override
    public String toString() {
        return "TodaySalesPo{" +
                "total_amount='" + total_amount + '\'' +
                ", total_num='" + total_num + '\'' +
                ", success_amount='" + success_amount + '\'' +
                ", success_num='" + success_num + '\'' +
                ", profit_amount='" + profit_amount + '\'' +
                '}';
    }
}
