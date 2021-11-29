package Reconsitution1;


import java.util.Scanner;

public class Sale {
    private SaledItem item;
    private Scanner input;

    Sale(SaledItem item){
        this.item = item;
        input = new Scanner(System.in);
    }

    Sale(){
        item = new SaledItem("./src/Goods.txt");
        input = new Scanner(System.in);
    }

    public void MakeNewSale(){
        String id;
        int num;
        System.out.println("是否录入商品");
        while (PosApp.GetCommand()) {
            System.out.print("输入商品id:");
            id = input.nextLine();
            System.out.print("输入数量:");
            num = input.nextInt();
            input.nextLine();
            item.AddItem(new SaledItemInfo(id,item.getNamebyID(id),num,item.getPricebyID(id)));
            ListSaledItem();
            System.out.println("是否录入商品");
        }
    }

    public void ListSaledItem(){
        System.out.println("商品名   零售价   数量   金额");
        for(var it:item.getSaled()){
            System.out.println(it);
        }
    }

    public SaledItem getItem() {
        return item;
    }

    public void MakePayment(){
        Payment pay = new Payment(this);
        pay.MakePayment();
    }

    public void PrintRecept(){
        Recept recept = new Recept(this,new Payment(this));
        recept.print();
    }
}
