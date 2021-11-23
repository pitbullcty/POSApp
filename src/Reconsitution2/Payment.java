package Reconsitution2;

public class Payment {

    private double pay;
    private double total;
    private double change;

    public Payment(double pay,Sale sale) {
        this.pay = pay;
        this.total = sale.getTotal();
        change = pay - total;
    }

    public boolean makePayment() {
        if (change >= 0) {
            return true;
        } else return false;
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
