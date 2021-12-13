package Reconsitution2.style;

import Reconsitution2.Payment;
import Reconsitution2.Recept;
import Reconsitution2.Sale;

public class SimpleAdapter implements Printer{
    private  SimplePrinter simplePrinter;
    @Override
    public void print(Sale sale, Payment payment) {
        simplePrinter = new SimplePrinter();
        simplePrinter.simplePrint(sale,payment);
    }
}
