package Reconsitution2;

import java.io.File;


public class GoodsLog {
    private File Log;
    private String Filename="./src/Goods.txt";

    GoodsLog(String filename){
        this.Log = new File(filename);
    }
    public File Getlog(){
        return this.Log;
    }

}
