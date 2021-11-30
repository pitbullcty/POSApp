package com.scu.pos;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


/*
*
* 单据类，用于打印单据，以及数据进入数据库
*
* */
public class Recept {

    private String text; //收据文本
    private String time; //交易时间
    private String detail;  //交易细节
    private double change;  //找零数额
    private double pay;  //实付金额
    private double total;  //应付金额
    private static long id=0; //交易id
    private Date date;  //交易日期

    public static final String URL = "jdbc:mysql://localhost:3306/pos";  //数据库url
    public static final String USER = "root";  //数据库用户
    public static final String PASSWORD = "123456"; //数据库密码

    /*
    * 构造函数，读入id
    * */
    public Recept(Sale sale, Payment payment){
        try {
            readId(); //读入id
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = new Date();
        time = formatter1.format(date); //格式化日期
        setText(sale,payment);  //设置文本
        change = payment.getChange();
        pay = payment.getPay();
        total = payment.getTotal();  //获取找零，付款额，实付额
        StringBuilder builder = new StringBuilder();
        String[][] text = sale.toTable();
        for(var e1:text){
            for(var e2:e1){
                builder.append(e2).append(' ');
            }
        }
        detail = builder.toString();  //获取交易细节
    }

    /*
    * 获取文本
    * */
    public String getText() {
        return text;
    }

    /*
    * 根据交易和实付额设置单据文本
    * */
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

    /*
    * 打印单据
    * */
    public void print(){
        String temptime = time.replace(':','-');
        File dir = new File("./src/logs");
        if(!dir.exists()) {
            dir.mkdirs();  //如果不存在目录则创建
        }
        String filename =  "./src/logs/Saleinfo " + temptime + ".txt";  //生成但就文件名
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            out.write(toString());  //单据文本写入单据文件
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * 数据库中读入Id
    * */
    public long readId() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");  //加载驱动
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);  //获取链接
        String sql = "select count(*) from sale_info";  //构造sql语句
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);  //执行sql
        if(rs.next()){
            id = rs.getLong(1);
        } //获取id
        else id=0;
        return id;
    }

    /*
    * 交易信息写入数据库
    * */
    public void toDataBase() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");  //加载驱动
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); //获取链接
        String sql = "INSERT INTO sale_info(id,total,pay,`change`,detail,`date`)"
                +"values (?,?,?,?,?,?)";  //构造sql语句
        //预编译
        PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行
        ptmt.setLong(1, id);
        ptmt.setDouble(2,total );
        ptmt.setDouble(3, pay);
        ptmt.setDouble(4, change);
        ptmt.setString(5,detail);
        ptmt.setDate(6,new java.sql.Date(date.getTime()));  //设置sql参数
        ptmt.execute(); //执行sql语句
    }

}
