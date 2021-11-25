package Reconsitution2;


import Reconsitution2.state.*;

public class PosApp {

    private Payment payment;
    private Sale sale;
    private boolean isdone = false;
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean makeNewSale() {
        boolean res;
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WelcomeUI welcomeUI = WelcomeUI.getInstance();
        res = welcomeUI.getIsdone();
        if (welcomeUI.getIsdone()) {
            welcomeUI.setNull();
        }
        return res;
    }

    public boolean enterItem() {
        boolean res;
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SaleUI saleUI = SaleUI.getInstance();
        sale = SaleUI.getInstance().getSale();
        res = saleUI.getIsdone();
        if (saleUI.getIsdone()) {
            SaleUI.setNull();
        }
        return res;
    }

    public boolean makePayment() {
        boolean res;
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PaymentUI paymentUI = PaymentUI.getInstance(sale);
        payment = paymentUI.getPayment();
        res = paymentUI.getIsdone();
        if (paymentUI.getIsdone()) {
            PaymentUI.setNull();
        }
        return res;
    }

    public boolean finishSale() {
        boolean res;
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ReceptUI receptUI = ReceptUI.getInstance(sale, payment);
        res = receptUI.getIsdone();
        if (receptUI.getIsdone()) {
            ReceptUI.setNull();
        }
        return res;
    }

    public void run() {
        while (true) {
            NewSaleState newSaleState = new NewSaleState();
            newSaleState.makeNewSale(this);
            SaleState saleState = new SaleState();
            saleState.enterItem(this);
            PaymentState paymentState = new PaymentState();
            paymentState.makePayment(this);
            FinishState finishState = new FinishState();
            finishState.finishSale(this);
        }
    }

    public static void main(String[] args) {
        PosApp app = new PosApp();
        app.run();
    }
}
