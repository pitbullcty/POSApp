package Reconsitution1;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


class SaledItemInfo extends Item {
    private int count;

    SaledItemInfo(String id,String name, int count, double price) {
        super(id,name,price);
        this.count = count;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format("%s   %.2f   %d   %.2f", name, price, count, count * price);
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
            if(info.getID().equals(ID)){
                return info.getName();
            }
        }
        return null;
    }

    public double getPricebyID(String ID){
        for(var info:Total.getInfo()){
            if(info.getID().equals(ID)){
                return info.getPrice();
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

