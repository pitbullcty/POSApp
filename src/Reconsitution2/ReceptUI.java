package Reconsitution2;
import javax.swing.*;
import java.awt.event.ActionListener;

public class ReceptUI {
    private JFrame frame;
    private JLabel info;
    private JPanel panel;
    private JProgressBar  processBar;
    private Recept recept;

    public ReceptUI(Sale sale,Payment payment){
        frame = new JFrame("POS系统");
        frame.setContentPane(panel);
        frame.setSize(400,200);
        frame.setVisible(true);
    }

  /*  public void print(){
        for(int i=0;i<100;i++){
            processBar.setValue(i);
            recept.print();
            processBar.setString("正在打印单据"+i+"%");
            try {
                Thread.sleep(200);
            }catch (Exception e){
                ;
            }
        }
    }*/

}
