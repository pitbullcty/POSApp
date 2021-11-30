package com.scu.pos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
* Item类，用于记录商品信息
*
* */
class Item {
    protected String ID; //商品ID
    protected String name; //商品名字
    protected double price; //商品价格

    /*
    * 构造方法
    * */
    Item(String ID, String name, double price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String toString(){
        return String.format("%s   %s   %.2f",ID,name,price);
    }
}

/*
* GoodsInfo 用于记录商品信息
* */
public class GoodsInfo{

    ArrayList<Item> Info;  //商品信息
    GoodsLog log; //商品记录文件

    /*
    * 构造方法
    * */
    GoodsInfo(String filename) {
        this.log = new GoodsLog(filename);
        this.Info = new ArrayList<>();
        this.readInfo();
    }

    /*
    * 从文件读入商品信息
    * */
    public void readInfo(){
        File logs = log.Getlog();
        Scanner input1 = null;
        try {
            input1 = new Scanner(logs); //以文件构造Scannner
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        input1.nextLine();
        while (input1.hasNext()) {
            String line = input1.nextLine();
            String ID = line.substring(0,3);
            Scanner input2 = new Scanner(line.substring(4));
            String name = input2.next();
            double price = input2.nextDouble();  //按照文件写入规则读入商品信息
            this.Info.add(new Item(ID, name, price));  //将商品信息添加至Arraylist
            input2.close();
        }
    }

    public ArrayList<Item> getInfo() {
        return Info;
    }

}


