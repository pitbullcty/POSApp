package Reconsitution2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Recept {

    private String text;

    public Recept(Sale sale, Payment payment){
        setText(sale,payment);
    }

    public String getText() {
        return text;
    }

    public void setText(Sale sale,Payment payment){
        StringBuilder builder = new StringBuilder();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        builder.append("商品名   零售价   数量   金额\n\n");
        for(var info:sale.getItem().getSaled()){
            builder.append(info+"\n");
        }
        builder.append("\n").append(String.format("应付额：%.2f\n", payment.getTotal()));
        builder.append(String.format("实付额：%.2f\n", payment.getPay()));
        builder.append(String.format("找零:%.2f\n", payment.getChange()));
        builder.append("交易时间:" +  formatter1.format(new Date()) +"\n");
        builder.append("交易地点：四川大学江安校区\n");
        builder.append("交易人：小明\n");
        text = builder.toString();
    }

    @Override
    public String toString() {
       return text;
    }

    public void print(){
        Date date = new Date();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        File dir = new File("./src/logs");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        String filename =  "./src/logs/Saleinfo " + formatter1.format(date) + ".txt";
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            out.write(toString());
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
