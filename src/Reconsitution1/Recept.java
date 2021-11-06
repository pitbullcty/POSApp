package Reconsitution1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Recept {

    private Payment pay;

    Recept(Payment pay){
        this.pay =pay;
    }

    public void PrintRecept(){
        double payment = pay.getPayment();
        double sum = pay.getSum();
        Date date = new Date();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        File dir = new File("./src/logs");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        String filename =  "./src/logs/Saleinfo " + formatter1.format(date) + ".txt";
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            out.write("商品名   零售价   数量   金额\n\n");
            for (var info : pay.getSale().getItem().getSaled()) {
                out.write(info + "\n");
            }
            out.write("\n");
            out.write(String.format("应付额：%.2f\n", sum));
            out.write(String.format("实付额：%.2f\n", payment));
            out.write(String.format("找零:%.2f\n", payment - sum));
            out.write("交易时间:" +  formatter2.format(date) +"\n");
            out.write("交易地点：四川大学江安校区\n");
            out.write("交易人：小明\n");
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
