package com.scu.pos;


/*
* PosApp主类 main函数入口
* */
public class PosApp {

    private Payment payment; //付款信息
    private Sale sale;  //销售信息
    private boolean isdone = false; //界面是否跳转
    private enum STATE {
        WELCOMESTATE,  //欢迎状态
        SALESTATE,  //销售状态爱
        PAYMENTSTATE, //付款状态
        RECEPTSTATE,  //打印状态
    }  //界面状态

    private STATE state = STATE.WELCOMESTATE; //输出状态为欢迎状态

    /*
    * 进行新销售
    * */
    public void makeNewSale() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WelcomeUI welcomeUI = WelcomeUI.getInstance(); //获取欢迎界面实例
        if (welcomeUI.getIsdone()) {
            state = STATE.SALESTATE; //跳转至销售界面
            WelcomeUI.setNull();  //销毁销售界面
        }
    }

    /*
    * 输入物品
    * */
    public void enterItem() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SaleUI saleUI = SaleUI.getInstance();  //获取销售界面实例
        sale = saleUI.getSale();
        if (saleUI.getIsdone()) {
            state = STATE.PAYMENTSTATE;  //跳转至付款状态
            SaleUI.setNull();  //销毁销售界面
        }
    }

    /*
    * 付款
    * */
    public void makePayment() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PaymentUI paymentUI = PaymentUI.getInstance(sale); //获取付款界面实例
        payment = paymentUI.getPayment();
        if (paymentUI.getIsdone()) {
            state = STATE.RECEPTSTATE; //跳转至收款状态
            PaymentUI.setNull(); //销毁付款界面
        }
    }

    /*
    * 打印单据
    * */
    public void print() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ReceptUI receptUI = ReceptUI.getInstance(sale, payment);  //获取销售界面实例
        if (receptUI.getIsdone()) {
            state = STATE.WELCOMESTATE;  //跳转至欢迎状态
            ReceptUI.setNull();   //销毁收据界面
        }
    }

    /*
    * 运行
    * */
    public void run() {
        while (true) {
            switch (state) {
                case WELCOMESTATE:
                    makeNewSale();
                    break;
                case SALESTATE:
                    enterItem();
                    break;
                case PAYMENTSTATE:
                    makePayment();
                    break;
                case RECEPTSTATE:
                    print();
                    break;
                default:
                    break;
            }  //switch-case结构实现跳转界面
        }
    }

    public static void main(String[] args) {
        PosApp app = new PosApp();
        app.run();
    }
}
