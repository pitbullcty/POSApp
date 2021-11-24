package Reconsitution2;


import java.util.ArrayList;


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

    public SaledItem(ArrayList<SaledItemInfo> Saled) {
        this.Saled = Saled;
        sum = 0;
    }

    public SaledItem() {
        Saled = new ArrayList<>();
        sum = 0;
    }

    public void addItem(SaledItemInfo item) {
        boolean isrepeated = false;
        for (var e : Saled) {
            if (e.getName().equals(item.getName())) {
                isrepeated = true;
                e.setCount(e.getCount() + item.getCount());
                break;
            }
        }
        if (!isrepeated) Saled.add(item);
        sum += item.getCount() * item.getPrice();
    }

    public ArrayList<SaledItemInfo> getSaled() {
        return Saled;
    }

    public String getNamebyID(String ID, GoodsInfo info) {
        for (var e : info.getInfo()) {
            if (e.getID().equals(ID)) {
                return e.getName();
            }
        }
        return null;
    }

    public double getPricebyID(String ID, GoodsInfo info) {
        for (var e : info.getInfo()) {
            if (e.getID().equals(ID)) {
                return e.getPrice();
            }
        }
        return 0;
    }

    public double getSum() {
        return sum;
    }
}

