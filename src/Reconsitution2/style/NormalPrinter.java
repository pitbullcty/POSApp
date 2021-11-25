package Reconsitution2.style;

import Reconsitution2.Recept;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NormalPrinter {
    public void normalPrint(Recept recept){
        String time = recept.getTime();
        String text = recept.getText();
        File dir = new File("./src/logs");
        if(!dir.exists()) {
            boolean success = dir.mkdirs();
        }
        String filename =  "./src/logs/正常类型单据 " + time + ".txt";
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            out.write(text);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
