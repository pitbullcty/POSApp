package Reconsitution1;


import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class Payment {
    private Sale Payment;
    private double payment;
    private double sum;
    private Scanner input;

    Payment(Sale sale){
        Payment = sale;
        input = new Scanner(System.in);
        sum = Payment.getItem().getSum();
    }

    public void MakePayment(){
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

    public double getPayment() {
        return payment;
    }

    public double getSum() {
        return sum;
    }

    public Sale getSale(){
        return Payment;
    }
}
