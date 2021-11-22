package Reconsitution2;

import javax.swing.*;

public class AddUI {
    private JFrame frame;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;

    private JButton enter;

    public JFrame getFrame() {
        return frame;
    }

    public AddUI(){
        frame = new JFrame("POS系统");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400,200);
        frame.setVisible(true);
    }

    public void enterItem(){
        enter.addActionListener(e->{

        });
    }


    public void setDefault() {
        textField1.setText("");
        textField2.setText("");
    }
}
