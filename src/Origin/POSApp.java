package Origin;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class POSApp {


    private ArrayList<String> Info;
    private double sum;
    private double payment;
    private Scanner input;

    public static void main(String[] args) {
        POSApp POS = new POSApp();
        POS.Run();
    }

    public POSApp() {
        Info = new ArrayList<>();
        sum = 0;
        payment = 0;
        input = new Scanner(System.in);
    }

    public void Run() {
        while (true) {
            if (Startup()) {
                MakeSale();
                MakePayment();
                FinishSale(payment);
            } else {
                System.out.println("等待下次交易！");
                System.out.println("............");
                System.out.println("............");
                System.out.println("............");
            }
        }
    }

    public boolean GetCommand() {

        char cmd = input.nextLine().charAt(0);
        while (true) {
            if (cmd == 'Y' || cmd == 'y') {
                return true;
            } else if (cmd == 'N' || cmd == 'n') {
                return false;
            } else {
                System.out.println("指令错误！请重试");
            }
            cmd = input.nextLine().charAt(0);
        }
    }

    public boolean Startup() {
        System.out.println("正在初始化系统...");
        sum = 0;
        payment = 0;
        Info.clear();
        System.out.println("要开始本次交易吗？");
        return GetCommand();
    }

    public void EnterItem(String id, int num) {
        Scanner list = null;
        String line = "";
        File info = new File("./src/Goods.txt");
        try {
            list = new Scanner(info);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        list.nextLine();
        while (list.hasNext()) {
            line = list.nextLine();
            if (line.indexOf(id) != -1) {
                line = line.substring(6);
                Scanner getdouble = new Scanner(line);
                getdouble.next();
                double price = getdouble.nextDouble();
                sum = sum + num * price;
                Info.add(String.format("%s    %d   %.2f", line, num, num * price));
                getdouble.close();
            }
        }
        list.close();
    }

    public void ShowItem() {
        System.out.println("商品名   零售价   数量   金额");
        for (var info : Info) {
            System.out.println(info);
        }
    }


    public void MakeSale() {
        String id = "";
        int num = 0;
        System.out.println("是否录入商品");
        while (GetCommand()) {
            System.out.print("输入商品id:");
            id = input.nextLine();
            System.out.print("输入数量:");
            num = input.nextInt();
            input.nextLine();
            EnterItem(id, num);
            ShowItem();
            System.out.println("是否录入商品");
        }
    }

    public void FinishSale(double payment) {
        Date date = new Date();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        File dir = new File("./src/logs");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        String filename =  "./src/logs/Saleinfo " + formatter1.format(date) + ".txt";
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            out.write("商品名   零售价   数量   金额\n\n");
            for (var info : Info) {
                out.write(info + "\n");
            }
            out.write("\n");
            out.write(String.format("应付额：%.2f\n", sum));
            out.write(String.format("实付额：%.2f\n", payment));
            out.write(String.format("找零:%.2f\n", payment - sum));
            out.write("交易时间:" +  formatter2.format(date) +"\n");
            out.write("交易地点：四川大学江安校区\n");
            out.write("交易人：小明\n");
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MakePayment() {
        System.out.println(String.format("应付额：%.2f", sum));
        while (true) {
            System.out.println("输入实付额:");
            payment = input.nextDouble();
            System.out.println("实付额:" + payment);
            if (payment - sum >= 0) {
                System.out.println(String.format("找零:%.2f", payment - sum));
                System.out.println("本次交易完成！");
                input.nextLine();
                break;
            } else {
                System.out.println("实付额错误！（再次输入）");
            }
        }
    }
}
