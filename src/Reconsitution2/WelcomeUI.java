package Reconsitution2;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;

public class WelcomeUI {

    private JFrame frame;
    private JPanel panel1;
    private JButton start;

    void makeNewSale() {
        start.addActionListener(e -> {
            SaleUI ui = new SaleUI();
            frame.setVisible(false);
        });
    }

    public WelcomeUI() {
        frame = new JFrame("POS系统");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        makeNewSale();
    }


}
