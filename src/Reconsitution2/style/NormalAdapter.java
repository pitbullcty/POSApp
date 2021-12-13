package Reconsitution2.style;

import Reconsitution2.Payment;
import Reconsitution2.Recept;
import Reconsitution2.Sale;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NormalAdapter implements Printer {
    private NormalPrinter normalPrinter;
    @Override
    public void print(Sale sale, Payment payment) {
        normalPrinter = new NormalPrinter();
        normalPrinter.normalPrint(sale,payment);
    }
}
