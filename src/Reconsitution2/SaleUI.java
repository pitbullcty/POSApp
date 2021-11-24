package Reconsitution2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SaleUI {

    private static SaleUI ui = null;
    private boolean isdone = false;
    private JFrame frame;
    private JPanel panel;
    private JTable item;
    private JButton add;
    private JButton end;
    private JLabel time;
    private JScrollPane scrollPane1;
    private Sale sale;
    private AddUI addui;
    private PaymentUI payui;


    private class TimeActionListener implements ActionListener {
        public TimeActionListener() {
            Timer t = new Timer(1000, this);
            t.start();
        }

        @Override
        public void actionPerformed(ActionEvent ac) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            showItem();
            time.setText("当前时间： " + formatter.format(new Date()));
        }
    }
    //定时内部类

    public static SaleUI getInstance() {
        if (ui == null) ui = new SaleUI();
        return ui;
    }

    public SaleUI() {
        sale = new Sale();
        frame = new JFrame("POS系统");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        setTable();
        add.addActionListener(new TimeActionListener());
        frame.setVisible(true);
        isdone = false;
        enterItem();
        finishSale();
    }


    public boolean getIsdone() {
        return isdone;
    }


    public static void setNull() {
        ui = null;
    }

    public void enterItem() {
        add.addActionListener(e -> {
            if (addui == null) addui = new AddUI();
            else {
                addui.setDefault();
                addui.getFrame().setVisible(true);
            }
        });
    }

    public void setTable() {
        String[] columns = {"商品名", "价格", "数量"};
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        item.setModel(model);
    }

    public void showItem() {
        setTable();
        if (addui != null) {
            sale.setItem(addui.getSaled());
            sale.setTotal(addui.getSaled().getSum());
            DefaultTableModel defaultTableModel = (DefaultTableModel) item.getModel();
            for (var row : sale.toTable()) {
                defaultTableModel.addRow(row);
            }
            item.setModel(defaultTableModel);
        }
    }

    public void finishSale() {
        end.addActionListener(e -> {
            if (addui != null) addui.getFrame().setVisible(false);
            frame.setVisible(false);
            isdone = true;
        });
    }

    public Sale getSale() {
        return sale;
    }
}
