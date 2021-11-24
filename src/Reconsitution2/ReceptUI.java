package Reconsitution2;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicButtonListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceptUI {
    private JFrame frame;
    private JLabel info;
    private JPanel panel;
    private JProgressBar processBar;
    private Recept recept;

    public ReceptUI(Sale sale, Payment payment) {
        frame = new JFrame("POS系统");
        frame.setContentPane(panel);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        recept = new Recept(sale, payment);
        processBar.addChangeListener(e -> {
            if (processBar.getValue() == 100) {
                frame.dispose();
                WelcomeUI ui = new WelcomeUI();
            }
        });
    }

    class PrintAction extends Thread {
        int count = 0;

        public int getCount() {
            return count;
        }

        public void run() {
            boolean isInvoke = false;
            while (count < 100) {
                if (!isInvoke) recept.print();
                isInvoke = true;
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    count++;
                }
            }
        }
    }


    public void print() {
        PrintAction printAction = new PrintAction();
        printAction.start();
        Timer timer = new Timer(100, e -> {
            processBar.setValue(printAction.getCount());
            info.setText("正在打印单据 完成" + printAction.getCount() + "%");
        });
        timer.start();
    }

}
