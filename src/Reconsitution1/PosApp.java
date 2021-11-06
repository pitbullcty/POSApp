package Reconsitution1;

import java.util.Scanner;

public class PosApp {
    private Sale sale;
    PosApp(Sale sale){
      this.sale=sale;
    }
    PosApp(){
        sale = new Sale();
    }

    public static boolean GetCommand() {
        Scanner input = new Scanner(System.in);
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

    public boolean Startup(){
        System.out.println("正在初始化系统...");
        System.out.println("要开始本次交易吗？");
        return GetCommand();
    }

    public void Run() {
        while (true) {
            if (Startup()) {
               sale.MakeNewSale();
               sale.MakePayment();
               sale.PrintRecept();
            } else {
                System.out.println("等待下次交易！");
                System.out.println("............");
                System.out.println("............");
                System.out.println("............");
            }
        }
    }

    public static void main(String[] args) {
        PosApp pos = new PosApp();
        pos.Run();
    }
}
