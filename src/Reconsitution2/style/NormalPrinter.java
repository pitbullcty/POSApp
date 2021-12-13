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

public class NormalPrinter {
    public void normalPrint(Sale sale, Payment payment){
        String time ="";
        StringBuilder builder = new StringBuilder();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time =  formatter1.format(new Date());
        time = time.replace(':','-');
        builder.append("商品名   零售价   数量   金额\n\n");
        for(var info:sale.getItem().getSaled()){
            builder.append(info).append("\n");
        }
        builder.append("\n").append(String.format("应付额：%.2f\n", payment.getTotal()));
        builder.append(String.format("实付额：%.2f\n", payment.getPay()));
        builder.append(String.format("找零:%.2f\n", payment.getChange()));
        builder.append("交易时间:").append(time).append("\n");
        builder.append("交易地点：四川大学江安校区\n");
        builder.append("交易人：小明\n");
        String text = builder.toString();
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
