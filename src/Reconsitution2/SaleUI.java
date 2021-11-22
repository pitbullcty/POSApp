package Reconsitution2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;


public class SaleUI {
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
        public TimeActionListener(){
            Timer t=new Timer(1000,this);
            t.start();
        }

        @Override
        public void actionPerformed(ActionEvent ac){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            showItem();
            time.setText("当前时间： "+formatter.format(new java.util.Date()).toString());
        }
    }
    //定时内部类


    public SaleUI(){
        sale = new Sale();
        frame = new JFrame("POS系统");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        setTable();
        add.addActionListener(new TimeActionListener());
        frame.setVisible(true);
        enterItem();
        finishSale();
    }

    public JPanel getPanel1() {
        return panel;
    }

    public void enterItem(){
        add.addActionListener(e->{
           if(addui==null) addui = new AddUI();
           else {
               addui.setDefault();
               addui.getFrame().setVisible(true);
           }
        });
    }


    public void setTable(){
        String[] columns = {"商品名","价格","数量"};
        Object[][] data=null;
        DefaultTableModel model = new DefaultTableModel(data,columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        item.setModel((TableModel) model);
    }


    public void showItem(){
        setTable();
        if(addui!=null){
            sale.setItem(addui.getSaled());
            DefaultTableModel defaultTableModel = (DefaultTableModel)item.getModel();
            for(var row:sale.toTable()){
                defaultTableModel.addRow(row);
            }
            item.setModel((TableModel) defaultTableModel);
        }
    }

    public void finishSale(){
       end.addActionListener(e -> {
           if(addui!=null) addui.getFrame().setVisible(false);
           payui = new PaymentUI();
           frame.setVisible(false);
       });
    }


}
