package com.scu.pos;


import java.util.ArrayList;

/*
* SaledItemInfo类，记录卖出商品信息 继承自商品信息
* */
class SaledItemInfo extends Item {

    private int count; //卖出商品数量

    /*
    * 构造方法
    * */
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


/*
* SaleItem类 用于存储卖出商品信息
* */
public class SaledItem {
    private double sum;  //商品价格和
    private ArrayList<SaledItemInfo> Saled;  //卖出商品信息

    /*
    *构造方法
    * */
    public SaledItem(ArrayList<SaledItemInfo> Saled) {
        this.Saled = Saled;
        sum = 0;
    }

    /*
    * 无参构造方法
    * */
    public SaledItem() {
        Saled = new ArrayList<>();
        sum = 0;
    }

    /*
    * 添加新销售物品
    * */
    public void addItem(SaledItemInfo item) {
        boolean isrepeated = false;
        for (var e : Saled) {
            if (e.getName().equals(item.getName())) {
                isrepeated = true;
                e.setCount(e.getCount() + item.getCount());
                break;
            }  //判断是否重复添加同类商品
        }
        if (!isrepeated) Saled.add(item);
        sum += item.getCount() * item.getPrice(); //计算总计
    }

    public ArrayList<SaledItemInfo> getSaled() {
        return Saled;
    }

    /*
    * 按照商品ID获取商品名
    * */
    public String getNamebyID(String ID, GoodsInfo info) {
        for (var e : info.getInfo()) {
            if (e.getID().equals(ID)) {
                return e.getName();
            }
        }
        return null;
    }

    /*
    * 按照商品ID获取价格
    * */
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

