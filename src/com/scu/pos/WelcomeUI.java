package com.scu.pos;

import javax.swing.*;

/*
* WelcomeUI 欢迎界面类
* */
public class WelcomeUI {

    private static WelcomeUI ui;
    private static boolean isdone = false;
    private JFrame frame; //框架
    private JPanel panel1; //画板
    private JButton start;  //开始销售按钮

    /*
    * 开始新销售
    * */
    void makeNewSale() {
        start.addActionListener(e -> {
            isdone = true;
            frame.dispose();
        }); //为开始按钮添加监听事件
    }

    public boolean getIsdone() {
        return isdone;
    }


    public static void setNull() {
        ui = null;
    }

    /*
    * 获取单例类实例
    * */
    public static WelcomeUI getInstance() {
        if (ui == null) ui = new WelcomeUI();
        return ui;
    }

    /*
    * 构造方法
    * */
    public WelcomeUI() {
        frame = new JFrame("超市购物系统");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        isdone = false;
        makeNewSale();
    }


}
