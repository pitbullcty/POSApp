package Reconsitution2;

import Reconsitution2.style.Printer;

import javax.swing.*;


public class ReceptUI {
    private static ReceptUI ui;
    private JFrame frame;
    private JLabel info;
    private JPanel panel;
    private JProgressBar processBar;
    private Recept recept;
    private boolean isdone = false;

    public ReceptUI(Sale sale, Payment payment) {
        frame = new JFrame("POS系统");
        frame.setContentPane(panel);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Object o =Parser.getObject();
        recept = new Recept(o,sale, payment);
        processBar.addChangeListener(e -> {
            if (processBar.getValue() == 100) {
                isdone = true;
                frame.setVisible(false);
            }
        });
        isdone = false;
        print();
    }


    public static void setNull() {
        ui = null;
    }

    public static ReceptUI getInstance(Sale sale, Payment payment) {
        if (ui == null) ui = new ReceptUI(sale, payment);
        return ui;
    }

    public boolean getIsdone() {
        return isdone;
    }


    class PrintAction extends Thread {
        int count = 0;

        public int getCount() {
            return count;
        }

        public void run() {
            boolean isInvoke = false;
            while (count < 100) {
                if (!isInvoke){
                    Object c =Parser.getObject();
                   Printer printer = (Printer) c;
                   printer.print(recept);
                }
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
