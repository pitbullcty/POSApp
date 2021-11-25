package Reconsitution2.state;

import Reconsitution2.PosApp;

public interface State {
    void makeNewSale(PosApp pos);

    void enterItem(PosApp pos);

    void finishSale(PosApp pos);

    void makePayment(PosApp pos);
}
