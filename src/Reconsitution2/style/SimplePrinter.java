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

public class SimplePrinter {

    public void simplePrint(Sale sale, Payment payment) {
        String time = "";
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = formatter1.format(new Date());
        time = time.replace(':', '-');
        StringBuilder builder = new StringBuilder();
        builder.append("商品名   零售价   数量   金额\n\n");
        for (var info : sale.getItem().getSaled()) {
            builder.append(info).append("\n");
        }
        String text = builder.toString();
        File dir = new File("./src/logs");
        if (!dir.exists()) {
            boolean success = dir.mkdirs();
        }
        String filename = "./src/logs/简单单据 " + time + ".txt";
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            out.write(text);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
