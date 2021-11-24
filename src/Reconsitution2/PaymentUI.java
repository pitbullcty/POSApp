package Reconsitution2;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class PaymentUI {

    private JFrame frame;
    private JTextField paid;
    private JLabel change;
    private JButton pay;
    private JPanel panel1;
    private JLabel total;
    private Payment payment;

    public PaymentUI(Sale sale) {
        frame = new JFrame("POS系统");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        setTotal(sale);
        makePayment(sale);
    }

    public void setTotal(Sale sale) {
        total.setText(String.valueOf(sale.getTotal()));
    }

    public void makePayment(Sale sale) {
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

        pay.addActionListener(e -> {
            try {
                if (payment.makePayment()) {
                    JOptionPane.showMessageDialog(panel1, "交易成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    ReceptUI ui = new ReceptUI(sale, payment);
                    ui.print();
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(panel1, "实付额小于应付额", "警告", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
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
