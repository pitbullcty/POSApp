package Reconsitution2;


interface State{
    void makeNewSale(PosApp pos);
    void enterItem(PosApp pos);
    void finishSale(PosApp pos);
    void makePayment(PosApp pos);
}

class NewSaleState implements State{

    @Override
    public void makeNewSale(PosApp pos) {
       while (!pos.makeNewSale()){
           pos.setState(this);
       }
    }

    @Override
    public void enterItem(PosApp pos) {

    }

    @Override
    public void finishSale(PosApp pos) {

    }

    @Override
    public void makePayment(PosApp pos) {

    }
}

class SaleState implements State{

    @Override
    public void makeNewSale(PosApp pos) {

    }

    @Override
    public void enterItem(PosApp pos) {
        while (!pos.enterItem()){
            pos.setState(this);
        }
    }

    @Override
    public void finishSale(PosApp pos) {

    }

    @Override
    public void makePayment(PosApp pos) {

    }
}

class PaymentState implements State{

    @Override
    public void makeNewSale(PosApp pos) {

    }

    @Override
    public void enterItem(PosApp pos) {

    }

    @Override
    public void finishSale(PosApp pos) {

    }

    @Override
    public void makePayment(PosApp pos) {
        while (!pos.makePayment()){
            pos.setState(this);
        }
    }
}

class FinishState implements State{

    @Override
    public void makeNewSale(PosApp pos) {

    }

    @Override
    public void enterItem(PosApp pos) {

    }

    @Override
    public void finishSale(PosApp pos) {
        while (!pos.finishSale()){
            pos.setState(this);
        }
    }

    @Override
    public void makePayment(PosApp pos) {

    }
}

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
