package Reconsitution2;

import javax.swing.*;

public class AddUI {
    private JFrame frame;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton enter;
    private SaledItem saled;
    private GoodsInfo info;

    public JFrame getFrame() {
        return frame;
    }

    public AddUI() {
        saled = new SaledItem();
        enterItem();
        frame = new JFrame("POS系统");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setVisible(true);
        saled = new SaledItem();
        info = new GoodsInfo("./src/Goods.txt");
    }

    public void enterItem() {
        enter.addActionListener(e -> {
            try{
                int num = Integer.parseInt(textField2.getText());
                String id = textField1.getText();
                String name= saled.getNamebyID(id,info);
                double price = saled.getPricebyID(id,info);
                if(name !=null){
                    saled.addItem(new SaledItemInfo(name,num,price));
                    JOptionPane.showMessageDialog(panel1, "成功添加商品", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(panel1, "无该商品ID，添加失败！", "警告", JOptionPane.WARNING_MESSAGE);
                }
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(panel1, "输入有误，添加失败！", "警告", JOptionPane.WARNING_MESSAGE);
            }

        });
    }


    public SaledItem getSaled() {
        return saled;
    }

    public void setDefault() {
        textField1.setText("");
        textField2.setText("");
    }
}
