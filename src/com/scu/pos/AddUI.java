package com.scu.pos;

import javax.swing.*;

/*
* AddUI 加购界面UI类
* */
public class AddUI {
    private JFrame frame; //框架
    private JPanel panel1; //画板
    private JTextField goodsID;  //商品id
    private JTextField goodsCount; //商品数量
    private JButton enter;  //输入物品
    private SaledItem saled;  //已销售物品
    private GoodsInfo info;  //商品信息

    public JFrame getFrame() {
        return frame;
    }

    /*
    * 构造方法
    * */
    public AddUI() {
        saled = new SaledItem();
        enterItem();
        frame = new JFrame("超市购物系统");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        saled = new SaledItem();
        info = new GoodsInfo("./src/Goods.txt");
    }


    /*
    * 添加物品
    * */
    public void enterItem() {
        enter.addActionListener(e -> {
            try {
                int num = Integer.parseInt(goodsCount.getText());
                String id = goodsID.getText(); //在输入框中获取商品id和名字
                String name = saled.getNamebyID(id, info);
                double price = saled.getPricebyID(id, info); //根据id获取商品对应的名字和价格
                if (name != null) {
                    saled.addItem(new SaledItemInfo(id,name, num, price));  //在已销售商品中添加物品
                    JOptionPane.showMessageDialog(panel1, "成功添加商品", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(panel1, "无该商品ID，添加失败！", "警告", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel1, "输入有误，添加失败！", "警告", JOptionPane.WARNING_MESSAGE);
            }
        }); //为添加物品按钮增加绑定事件
    }


    public SaledItem getSaled() {
        return saled;
    }

    public void setDefault() {
        goodsID.setText("");
        goodsCount.setText("");
    }

}
