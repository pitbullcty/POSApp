package Reconsitution2;

import javax.swing.*;

public class WelcomeUI {

    private JFrame frame;
    private JPanel panel1;
    private JButton start;

    void makeNewSale(){
        start.addActionListener(e->{
            SaleUI ui = new SaleUI();
            frame.setVisible(false);
        });
    }

    public WelcomeUI(){
        frame = new JFrame("POS系统");
        makeNewSale();
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        WelcomeUI wel = new WelcomeUI();
    }

}
