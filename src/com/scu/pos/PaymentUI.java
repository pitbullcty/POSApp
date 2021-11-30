package com.scu.pos;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/*
* PaymentUI 付款界面类
* */
public class PaymentUI {
    private static PaymentUI ui;
    private JFrame frame;  //框架
    private JTextField paid; //付款输入框
    private JLabel change; //找零
    private JButton pay; //付款按钮
    private JPanel panel1; //面板
    private JLabel total; //应付额
    private JTable table; //表格
    private Payment payment; //库款信息
    private boolean isdone ; //是否跳转

    /*
    * 构造方法
    * */
    public PaymentUI(Sale sale) {
        frame = new JFrame("超市购物系统");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        isdone = false;
        setTotal(sale);
        setTable(sale);
        addListner(sale);
        finishSale(sale);
    }

    /*
    * 设置表格
    * */
    public void setTable(Sale sale) {
        String[] columns = {"商品名", "价格", "数量"};
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }  //设置表格不可更改
        };
        table.setModel(model);
        DefaultTableModel defaultTableModel = (DefaultTableModel)  table.getModel();
        for (var row : sale.toTable()) {
            defaultTableModel.addRow(row);  //将销售数据加入表格
        }
        table.setModel(defaultTableModel);  //设置更改表格模型
    }

    /*
    * 获取单例类实例
    * */
    public static PaymentUI getInstance(Sale sale) {
        if (ui == null) ui = new PaymentUI(sale);
        return ui;
    }


    public static void setNull() {
        ui = null;
    }

    public void setTotal(Sale sale) {
        total.setText(String.valueOf(sale.getTotal()));
    }

    /*
    * 完成销售
    * */
    public void finishSale(Sale sale) {
        pay.addActionListener(e -> {
            try {
                if (payment.makePayment()) {  //如果付款成功
                    JOptionPane.showMessageDialog(panel1, "交易成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    isdone = true; //页面跳转
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(panel1, "实付额小于应付额", "警告", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    /*
    * 为文本框添加监听事件，文本发生变化时更新找零信息
    * */
    public void addListner(Sale sale){
        paid.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                getChange(sale);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                getChange(sale);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                getChange(sale);
            }
        });
    }

    public boolean getIsdone() {
        return isdone;
    }

    public void setIsdone(boolean isdone) {
        this.isdone = isdone;
    }

    public Payment getPayment() {
        return payment;
    }

    /*
    * 获取零钱金额
    * */
    public void getChange(Sale sale) {
        boolean isException = false;
        try {
            payment = new Payment(Double.parseDouble(paid.getText()), sale);  //新建付款对象
        } catch (Exception e) {
            isException = true;
            JOptionPane.showMessageDialog(panel1, "输入格式错误！", "警告", JOptionPane.WARNING_MESSAGE);
        } finally {
            if (!isException) change.setText(String.valueOf(payment.getChange()));
        }
    }

}
