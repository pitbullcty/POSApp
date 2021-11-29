package Reconsitution2;

public class PosApp {
    private Payment payment;
    private Sale sale;
    private boolean isdone = false;

    private enum STATE {
        WELCOMESTATE,
        SALESTATE,
        PAYMENTSTATE,
        RECEPTSTATE,
    }

    private STATE state = STATE.WELCOMESTATE;

    public void makeNewSale() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WelcomeUI welcomeUI = WelcomeUI.getInstance();
        if (welcomeUI.getIsdone()) {
            state = STATE.SALESTATE;
            WelcomeUI.setNull();
        }
    }

    public void enterItem() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SaleUI saleUI = SaleUI.getInstance();
        sale = SaleUI.getInstance().getSale();
        if (saleUI.getIsdone()) {
            state = STATE.PAYMENTSTATE;
            SaleUI.setNull();
        }
    }

    public void makePayment() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PaymentUI paymentUI = PaymentUI.getInstance(sale);
        payment = paymentUI.getPayment();
        if (paymentUI.getIsdone()) {
            state = STATE.RECEPTSTATE;
            PaymentUI.setNull();
        }
    }

    public void print() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ReceptUI receptUI = ReceptUI.getInstance(sale, payment);

        if (receptUI.getIsdone()) {
            state = STATE.WELCOMESTATE;
            ReceptUI.setNull();
        }
    }

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
            }
        }
    }

    public static void main(String[] args) {
        PosApp app = new PosApp();
        app.run();
    }
}
