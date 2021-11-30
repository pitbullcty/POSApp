package com.scu.pos;

import javax.swing.*;


public class WelcomeUI {

    private static WelcomeUI ui = null;
    private static boolean isdone = false;
    private JFrame frame;
    private JPanel panel1;
    private JButton start;

    void makeNewSale() {
        start.addActionListener(e -> {
            isdone = true;
            frame.setVisible(false);
        });
    }

    public boolean getIsdone() {
        return isdone;
    }




    public static void setNull() {
        ui = null;
    }


    public static WelcomeUI getInstance() {
        if (ui == null) ui = new WelcomeUI();
        return ui;
    }

    public WelcomeUI() {
        frame = new JFrame("POS系统");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        isdone = false;
        makeNewSale();
    }


}
