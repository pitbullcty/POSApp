package com.scu.pos;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class PaymentUI {
    private static PaymentUI ui;
    private JFrame frame;
    private JTextField paid;
    private JLabel change;
    private JButton pay;
    private JPanel panel1;
    private JLabel total;
    private JTable table;
    private Payment payment;
    private boolean isdone ;

    public PaymentUI(Sale sale) {
        frame = new JFrame("POS系统");
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

    public void setTable(Sale sale) {
        String[] columns = {"商品名", "价格", "数量"};
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);
        DefaultTableModel defaultTableModel = (DefaultTableModel)  table.getModel();
        for (var row : sale.toTable()) {
            defaultTableModel.addRow(row);
        }
        table.setModel(defaultTableModel);
    }


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

    public void finishSale(Sale sale) {
        pay.addActionListener(e -> {
            try {
                if (payment.makePayment()) {
                    JOptionPane.showMessageDialog(panel1, "交易成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    isdone = true;
                    frame.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(panel1, "实付额小于应付额", "警告", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

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

    public void getChange(Sale sale) {
        boolean isException = false;
        try {
            payment = new Payment(Double.parseDouble(paid.getText()), sale);
        } catch (Exception e) {
            isException = true;
            JOptionPane.showMessageDialog(panel1, "输入格式错误！", "警告", JOptionPane.WARNING_MESSAGE);
        } finally {
            if (!isException) change.setText(String.valueOf(payment.getChange()));
        }
    }

}
