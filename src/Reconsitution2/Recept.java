package Reconsitution2;

import Reconsitution2.style.SimpleAdapter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Recept {

    private String text;
    private String time;

    public Recept(Object o,Sale sale, Payment payment){
        setText(o,sale,payment);
    }

    public void setText(Object o,Sale sale,Payment payment){
        if(o instanceof SimpleAdapter){
            setSimpleText(sale,payment);
        }
        else{
            setNormalText(sale,payment);
        }
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public void setNormalText(Sale sale, Payment payment){
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
        text = builder.toString();
    }

    public void setSimpleText(Sale sale,Payment payment){
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time =  formatter1.format(new Date());
        time = time.replace(':','-');
        StringBuilder builder = new StringBuilder();
        builder.append("商品名   零售价   数量   金额\n\n");
        for(var info:sale.getItem().getSaled()){
            builder.append(info).append("\n");
        }
        text = builder.toString();
    }

    @Override
    public String toString() {
        return text;
    }


}

