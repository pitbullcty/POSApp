package com.scu.pos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
