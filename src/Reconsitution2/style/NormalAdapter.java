package Reconsitution2.style;

import Reconsitution2.Recept;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NormalAdapter implements Printer {
    private NormalPrinter normalPrinter;
    @Override
    public void print(Recept recept) {
        normalPrinter = new NormalPrinter();
        normalPrinter.normalPrint(recept);
    }
}
