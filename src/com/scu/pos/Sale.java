package com.scu.pos;
import java.util.ArrayList;


/*
* Sale类，用于记录销售信息
* */
public class Sale {

    private SaledItem item;  //卖出物品信息
    private double total;  //总价

    /*
    *构造方法
    * */
    public Sale(SaledItem item){
        this.item = item;
        total = item.getSum();
    }

    public Sale(){
        item = null;
    }

    public SaledItem getItem() {
        return item;
    }

    public void setItem(SaledItem item) {
        this.item = item;
    }

    public double getTotal(){
        return total;
    }

    public void setTotal(double total) {
        this.total = item.getSum();
    }

    /*
    * 将销售信息转化为表格
    * */
    public String[][] toTable(){
        ArrayList<SaledItemInfo> info = item.getSaled();
        String[][] tableinfo = new String[info.size()][3];
        for(int i=0;i<info.size();i++){
            tableinfo[i][0] = info.get(i).getName();
            tableinfo[i][1] = String.valueOf(info.get(i).getPrice());
            tableinfo[i][2] = String.valueOf(info.get(i).getCount());
        }
        return tableinfo;
    }

}
