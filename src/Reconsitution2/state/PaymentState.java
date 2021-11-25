package Reconsitution2.state;

import Reconsitution2.PosApp;

public class PaymentState implements State {

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
        while (!pos.makePayment()) {
            pos.setState(this);
        }
    }
}
