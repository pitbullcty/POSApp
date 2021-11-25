package Reconsitution2.state;

import Reconsitution2.PosApp;

public class SaleState implements State {

    @Override
    public void makeNewSale(PosApp pos) {

    }

    @Override
    public void enterItem(PosApp pos) {
        while (!pos.enterItem()) {
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
