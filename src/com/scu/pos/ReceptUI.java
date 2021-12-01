package com.scu.pos;

import javax.swing.*;
import java.awt.*;

/*
 * ReceptUI 单据界面类
 * */
public class ReceptUI {

    private static ReceptUI ui;
    private JFrame frame;  //框架
    private JLabel info; //进度条信息
    private JPanel panel; //面板
    private JProgressBar processBar; //进度条
    private Recept recept; //收据
    private boolean isdone = false;  //是否需要跳转

    public ReceptUI(Sale sale, Payment payment) {
        setupUI();
        frame = new JFrame("超市购物系统");
        frame.setContentPane(panel);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        recept = new Recept(sale, payment);
        processBar.addChangeListener(e -> {
            if (processBar.getValue() == 100) {
                isdone = true;
                frame.dispose();
            }
        }); //添加进度条监听事件，进度条值为100时进行跳转
        isdone = false;
        print();
    }


    public static void setNull() {
        ui = null;
    }

    /*
     * 单例类获取实例
     * */
    public static ReceptUI getInstance(Sale sale, Payment payment) {
        if (ui == null) ui = new ReceptUI(sale, payment);
        return ui;
    }

    /*
     * 获取面板是否需要跳转
     * */
    public boolean getIsdone() {
        return isdone;
    }

    /*
    * 设置ui样式
    * */
    private void setupUI() {
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        info = new JLabel();
        info.setText("正在打印单据.......");
        panel.add(info, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        processBar = new JProgressBar();
        panel.add(processBar, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }


    public JComponent getRootComponent() {
        return panel;
    }

    /*
     * 打印内部类
     * */
    class PrintAction extends Thread {
        int count = 0; //进度条值

        public int getCount() {
            return count;
        }

        public void run() {
            boolean isInvoke = false;  //打印的方法是否调用
            while (count < 100) {
                if (!isInvoke) {
                    recept.print();
                    try {
                        recept.toDataBase();  //写入数据库
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(panel, "数据库配置出现错误！", "警告", JOptionPane.WARNING_MESSAGE);
                        e.printStackTrace();
                    }
                }
                isInvoke = true; //调用后设置为已调用
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    count++;
                }
            }
        }
    }

    /*
     * 打印事件
     * */
    public void print() {
        PrintAction printAction = new PrintAction();  //构造线程
        printAction.start();   //启动打印线程
        Timer timer = new Timer(100, e -> {
            processBar.setValue(printAction.getCount());
            info.setText("正在打印单据 完成" + printAction.getCount() + "%");
        });  //设置定时时间监听，每100ms执行一次，更新进度条和文字
        timer.start();
    }

}
