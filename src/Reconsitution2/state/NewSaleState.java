package Reconsitution2.state;

import Reconsitution2.PosApp;

public class NewSaleState implements State {

    @Override
    public void makeNewSale(PosApp pos) {
        while (!pos.makeNewSale()) {
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
