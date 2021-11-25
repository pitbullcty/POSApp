package Reconsitution2.style;

import Reconsitution2.Recept;

public class SimpleAdapter implements Printer{
    private  SimplePrinter simplePrinter;
    @Override
    public void print(Recept recept) {
        simplePrinter = new SimplePrinter();
        simplePrinter.simplePrint(recept);
    }
}
