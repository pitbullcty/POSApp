package Reconsitution2;

import Reconsitution2.style.Printer;
import Reconsitution2.style.SimpleAdapter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class Recept {

    private String sql;
   private Sale sale;
   private  Payment payment;

    public Recept(Sale sale, Payment payment){
        this.sale= sale;
        this.payment = payment;
    }

    public void setsql(Sale sale, Payment payment){
        sql = "insert into sale_info(id,total,pay,`change`,detail,`date`) values(?,";
        StringBuilder sqlBuilder = new StringBuilder(sql);
        StringBuilder detailbuild = new StringBuilder();
        sqlBuilder.append(payment.getTotal()+",");
        sqlBuilder.append(payment.getPay()+",");
        sqlBuilder.append(payment.getChange()+",");
        for(var e1:sale.toTable()){
            for (var e2:e1){
                detailbuild.append(e2).append(' ');
            }
        }
        SimpleDateFormat simpleFormatter  = new SimpleDateFormat("yyyy-MM-dd");

        sqlBuilder.append("'"+detailbuild+"',");
        sqlBuilder.append("'"+simpleFormatter.format(new Date())+"')");
        sql = sqlBuilder.toString();
    }



    public String getsql() {
        return sql;
    }

    public void setNormalText(Sale sale, Payment payment){

    }



    public void print() {
        Object c =Parser.getObject();
        Printer printer = (Printer) c;
        printer.print(sale,payment);
    }





}

