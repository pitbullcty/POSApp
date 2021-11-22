package Reconsitution2;
import java.util.ArrayList;

public class Sale {
    private SaledItem item;

    public  Sale(SaledItem item){
        this.item = item;
    }
    public Sale(){
        item = null;
    }

    public void setItem(SaledItem item) {
        this.item = item;
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
