package Reconsitution2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Item {
    private String ID;
    private String name;
    private double price;

    Item(String ID, String name, double price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    public String GetID() {
        return ID;
    }

    public String GetName() {
        return name;
    }

    public double GetPrice() {
        return price;
    }

    public String toString(){
        return String.format("%s   %s   %.2f",ID,name,price);
    }
}

public class GoodsInfo{
    ArrayList<Item> Info;
    GoodsLog log;

    GoodsInfo(String filename) {
        this.log = new GoodsLog(filename);
        this.Info = new ArrayList<>();
        this.ReadItem();
    }

    public void ReadItem(){
        File logs = log.Getlog();
        Scanner input1 = null;
        try {
            input1 = new Scanner(logs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        input1.nextLine();
        while (input1.hasNext()) {
            String line = input1.nextLine();
            String ID = line.substring(0,3);
            Scanner input2 = new Scanner(line.substring(4));
            String name = input2.next();
            double price = input2.nextDouble();
            this.Info.add(new Item(ID, name, price));
            input2.close();
        }
    }

    public ArrayList<Item> getInfo() {
        return Info;
    }
}


