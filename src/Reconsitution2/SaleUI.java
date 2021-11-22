package Reconsitution2;

import Reconsitution1.SaledItem;

import javax.swing.*;

public class SaleUI {
    private JFrame frame;
    private JPanel panel1;
    private JTable item;
    private JButton add;
    private JButton end;
    private JLabel time;
    private JLabel total;
    private SaledItem saledItem;
    private AddUI addui;
    private PaymentUI payui;

    public SaleUI(){
        frame = new JFrame("POS系统");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setVisible(true);
        enterItem();
        finishSale();
    }

    public JPanel getPanel1() {
        return panel1;
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

    public void finishSale(){
       end.addActionListener(e -> {
           if(addui!=null) addui.getFrame().setVisible(false);
           payui = new PaymentUI();
           frame.setVisible(false);
       });
    }

    public static void main(String[] args) {
       SaleUI ui = new SaleUI();
    }

}
