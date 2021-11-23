package Reconsitution2;
import java.util.ArrayList;

public class Sale {
    private SaledItem item;
    private double total;

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
