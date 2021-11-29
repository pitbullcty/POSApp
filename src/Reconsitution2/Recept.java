package Reconsitution2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Recept {

    private String text;
    private String time;
    private String detail;
    private double change;
    private double pay;
    private double total;
    private static long id=0;
    private Date date;

    public static final String URL = "jdbc:mysql://localhost:3306/pos";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";


    public Recept(Sale sale, Payment payment){
        try {
            readId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = new Date();
        time = formatter1.format(date);
        setText(sale,payment);
        change = payment.getChange();
        pay = payment.getPay();
        total = payment.getTotal();
        StringBuilder builder = new StringBuilder();
        String[][] text = sale.toTable();
        for(var e1:text){
            for(var e2:e1){
                builder.append(e2).append(' ');
            }
        }
        detail = builder.toString();
    }

    public String getText() {
        return text;
    }

    public void setText(Sale sale,Payment payment){
        StringBuilder builder = new StringBuilder();
        builder.append("商品名   零售价   数量   金额\n\n");
        for(var info:sale.getItem().getSaled()){
            builder.append(info+"\n");
        }
        builder.append("\n").append(String.format("应付额：%.2f\n", payment.getTotal()));
        builder.append(String.format("实付额：%.2f\n", payment.getPay()));
        builder.append(String.format("找零:%.2f\n", payment.getChange()));
        builder.append("交易时间:" + time +"\n");
        builder.append("交易地点：四川大学江安校区\n");
        builder.append("交易人：小明\n");
        text = builder.toString();
    }

    @Override
    public String toString() {
       return text;
    }

    public void print(){
        String temptime = time.replace(':','-');
        File dir = new File("./src/logs");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        String filename =  "./src/logs/Saleinfo " + temptime + ".txt";
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            out.write(toString());
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long readId() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "select count(*) from sale_info";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()){
            id = rs.getLong(1);
        }
        else id=0;
        return id;
    }

    public void toDataBase() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "INSERT INTO sale_info(id,total,pay,`change`,detail,`date`)"
                +"values (?,?,?,?,?,?)";
        //预编译
        PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行
        ptmt.setLong(1, id);
        ptmt.setDouble(2,total );
        ptmt.setDouble(3, pay);
        ptmt.setDouble(4, change);
        ptmt.setString(5,detail);
        ptmt.setDate(6,new java.sql.Date(date.getTime()));
        ptmt.execute();
    }

}
