package Reconsitution2;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PaymentUI {

    private JFrame frame;
    private JTextField paid;
    private JLabel change;
    private JButton pay;
    private JPanel panel1;
    private JLabel total;


    public  PaymentUI(){
        MakePayment();
        frame = new JFrame("POS系统");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setVisible(true);

    }

    public void MakePayment(){
        pay.addActionListener(e->{
            frame.setVisible(false);
            WelcomeUI ui = new WelcomeUI();
        });
        paid.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                getChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                getChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                getChange();
            }
        });
    }


    public void getChange(){
        boolean isException = false;
        double paid_count=0,total_count=0;
        try{
            paid_count = Double.parseDouble(paid.getText());
            total_count = Double.parseDouble(total.getText());
        }
        catch (Exception e){
            isException = true;
        }
        finally {
            if(!isException)  change.setText(String.valueOf(total_count-paid_count));
        }
    }
}
