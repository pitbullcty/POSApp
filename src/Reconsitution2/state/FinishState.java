package Reconsitution2.state;

import Reconsitution2.PosApp;

public class FinishState implements State {

    @Override
    public void makeNewSale(PosApp pos) {

    }

    @Override
    public void enterItem(PosApp pos) {

    }

    @Override
    public void finishSale(PosApp pos) {
        while (!pos.finishSale()) {
            pos.setState(this);
        }
    }

    @Override
    public void makePayment(PosApp pos) {

    }
}
