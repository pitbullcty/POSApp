package com.scu.pos;

/*
* Payment 用于存放付款信息
* */
public class Payment {

    private double pay; //付款金额
    private double total; //应付款总额
    private double change; //找零金额

    /*
    * 构造方法
    * */
    public Payment(double pay,Sale sale) {
        this.pay = pay;
        this.total = sale.getTotal();
        change = pay - total;
    }

    /*
    * 付款
    * */
    public boolean makePayment() {
        if (change >= 0) {  //找零金额大于零则付款成功
            return true;
        } else return false; //否则付款失败
    }

    public double getChange() {
        return change;
    }

    public double getPay() {
        return pay;
    }

    public double getTotal() {
        return total;
    }
}
