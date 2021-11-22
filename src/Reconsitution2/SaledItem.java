package Reconsitution2;



import java.util.ArrayList;


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

    public SaledItem(ArrayList<SaledItemInfo> Saled){
        this.Saled = Saled;
        sum = 0;
    }

    public SaledItem(){
        Saled = new ArrayList<>();
        sum=0;
    }

    public void addItem(SaledItemInfo item){
        this.Saled.add(item);
        sum +=item.getCount()*item.getPrice();
    }

    public ArrayList<SaledItemInfo> getSaled() {
        return Saled;
    }

    public String getNamebyID(String ID,GoodsInfo info){
        for(var e:info.getInfo()){
            if(e.GetID().equals(ID)){
                return e.GetName();
            }
        }
        return null;
    }

    public double getPricebyID(String ID,GoodsInfo info){
        for(var e:info.getInfo()){
            if(e.GetID().equals(ID)){
                return e.GetPrice();
            }
        }
        return 0;
    }

    public double getSum() {
        return sum;
    }
}

