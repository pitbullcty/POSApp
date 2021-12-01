package com.scu.pos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * SaleUI 销售界面类
 * */
public class SaleUI {

    private static SaleUI ui;
    private boolean isdone; //是否需要跳转
    private JFrame frame; //框架
    private JPanel panel; //画板
    private JTable item; //添加物品表格
    private JButton add; //添加按钮
    private JButton end; //结束按钮
    private JLabel time; //时间文本
    private JScrollPane scrollPane1;
    private Sale sale; //销售信息
    private AddUI addui; //加购界面


    private void setupUI() {
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 5, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("超市购物系统");
        panel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        add = new JButton();
        add.setText("加购");
        panel.add(add, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        end = new JButton();
        end.setText("结算");
        panel.add(end, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        time = new JLabel();
        time.setText("时间：");
        panel.add(time, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scrollPane1 = new JScrollPane();
        panel.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        item = new JTable();
        scrollPane1.setViewportView(item);
        final JLabel label2 = new JLabel();
        label2.setText("  ");
        panel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("");
        panel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(3, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("");
        panel.add(label4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }


    public JComponent getRootComponent() {
        return panel;
    }

    /*
     * 内部类，用于实现显示时间
     * */
    private class TimeActionListener implements ActionListener {
        public TimeActionListener() {
            Timer t = new Timer(1000, this);  //每一秒钟调用监听事件
            t.start();
        }

        @Override
        public void actionPerformed(ActionEvent ac) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            showItem();
            time.setText("当前时间： " + formatter.format(new Date()));  //更新当前时间
        }

    }

    /*
     * 获取SaleUI实例
     * */
    public static SaleUI getInstance() {
        if (ui == null) ui = new SaleUI();
        return ui;
    }

    /*
     * 构造方法
     * */
    public SaleUI() {
        setupUI();
        sale = new Sale();
        frame = new JFrame("超市购物系统");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        setTable();
        add.addActionListener(new TimeActionListener());
        frame.setVisible(true);
        isdone = false;
        enterItem();
        makePayment();
    }


    public boolean getIsdone() {
        return isdone;
    }

    public static void setNull() {
        ui = null;
    }


    /*
     * 添加物品监听事件
     * */
    public void enterItem() {
        add.addActionListener(e -> {
            if (addui == null) addui = new AddUI();
            else {
                addui.setDefault();
                addui.getFrame().setVisible(true);
            }
        });
    }

    /*
     * 设置表格默认样式
     * */
    public void setTable() {
        String[] columns = {"商品名", "价格", "数量"};
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            } //设置单元格不可修改
        };
        item.setModel(model);
    }

    /*
     * 显示已购商品
     * */
    public void showItem() {
        setTable();
        if (addui != null) {
            sale.setItem(addui.getSaled());
            sale.setTotal(addui.getSaled().getSum());
            DefaultTableModel defaultTableModel = (DefaultTableModel) item.getModel();
            for (var row : sale.toTable()) {
                defaultTableModel.addRow(row);
            } //获取已销售商品，添加进入表格
            item.setModel(defaultTableModel);
        }
    }

    /*
     * 付款
     * */
    public void makePayment() {
        end.addActionListener(e -> {
            if (sale.getItem() != null && sale.getItem().getSaled().size() != 0) { //当销售物品不为null且有已销售物品时
                if (addui != null) addui.getFrame().dispose();
                frame.dispose();
                isdone = true; //页面跳转
            } else {
                JOptionPane.showMessageDialog(panel, "请购买物品后结算！", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        }); //为付款按钮添加监听事件
    }

    public Sale getSale() {
        return sale;
    }
}
