package Reconsitution1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


class SaledItemInfo{
    private String name;
    private int count;
    private double price;

    SaledItemInfo(String name,int count,double price){
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return String.format("%s   %.2f   %d   %.2f",name,price,count,count*price);
    }
}

public class SaledItem {
    private double sum;
    private ArrayList<SaledItemInfo> Saled;
    private GoodsInfo Total;

    SaledItem(ArrayList<SaledItemInfo> Saled,String filename){
        this.Saled = Saled;
        Total = new GoodsInfo(filename);
        sum = 0;
    }

    SaledItem(String filename){
        Saled = new ArrayList<>();
        Total = new GoodsInfo(filename);
    }

    public void AddItem(SaledItemInfo item){
        this.Saled.add(item);
        sum +=item.getCount()*item.getPrice();
    }

    public ArrayList<SaledItemInfo> getSaled() {
        return Saled;
    }

    public String getNamebyID(String ID){
        for(var info:Total.getInfo()){
            if(info.GetID().equals(ID)){
                return info.GetName();
            }
        }
        return null;
    }

    public double getPricebyID(String ID){
        for(var info:Total.getInfo()){
            if(info.GetID().equals(ID)){
                return info.GetPrice();
            }
        }
        return 0;
    }

    public double getSum() {
        return sum;
    }

    public GoodsInfo getTotal() {
        return Total;
    }
}

