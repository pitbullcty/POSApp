package com.scu.pos;

import java.io.File;


/*
* GoodLog 商品目录类 记录目录文件
* */
public class GoodsLog {

    private File Log;  //商品目录文件
    private String Filename = "./src/Goods.txt";  //商品文件名

    /*
    * 构造函数
    * */
    GoodsLog(String filename) {
        this.Log = new File(filename);
    }

    public File Getlog() {
        return this.Log;
    }

}
