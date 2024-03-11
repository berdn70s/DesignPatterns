import java.util.ArrayList;
import java.util.Scanner;

class PotatoChips extends Products{


    public PotatoChips(int lowerBound, int upperBound, int price) {
        super(lowerBound, upperBound, price);
    }

    public boolean evaluate(int ch){
        if(ch>0 && ch<=20){
            System.out.println("Found Chips...");
            return true;
        }
        else{
            System.out.println("No product...");
            return false;
        }
    }
    public void push(){System.out.println("Pushing out the Potato Chips...");}
    public void drop(){System.out.println("You can now take your Chips...");}
}

class Soda extends Products{


    public Soda(int lowerBound, int upperBound, int price) {
        super(lowerBound, upperBound, price);
    }

    public boolean evaluate(int ch){
        if(ch>20 && ch<=40){
            System.out.println("Found Soda...");
            return true;
        }
        else{
            System.out.println("No product...");
            return false;
        }
    }
    public void push(){System.out.println("Pushing out the Soda...");}
    public void drop(){System.out.println("You can now take your Soda...");}
}

class Gummy extends Products{
    public Gummy(int lowerBound, int upperBound, int price) {
        super(lowerBound, upperBound, price);
    }

    public boolean evaluate(int ch){
        if(ch>40 && ch<=60){
            System.out.println("Found Gummy...");
            return true;
        }
        else{
            System.out.println("No product...");
            return false;
        }
    }
    public void push(){System.out.println("Pushing out the Gummy...");}
    public void drop(){System.out.println("You can now take your Gummy...");}
}

abstract class Products{

     protected int lowerBound;
     protected int upperBound;
     protected int price;

    public Products(int lowerBound, int upperBound, int price) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.price = price;
    }
    public abstract boolean evaluate(int ch);
    public abstract void push();
    public abstract void drop();

}
class VendingMachine{
    Scanner scan = new Scanner(System.in);

    ArrayList<Products> productList = new ArrayList();

    public void custChoice(int ch){
        for (int i = 0 ; i < 60 ; i++) {
            if (i >= 0 && i < 20) {
                productList.add(i, new PotatoChips(0, 20, 30));
            } else if (i >= 20 && i < 40) {
                productList.add(i, new Soda(20, 40, 25));
            } else if (i >= 40 && i < 60) {
                productList.add(i, new Gummy(40, 60, 15));
            }
        }
            if(productList.get(ch).evaluate(ch)){
                System.out.println("Last Step: Please put the money for the product you choose.\nChips: 30\nSoda: 15\nGummy: 25");
                int money = scan.nextInt();
                if(money >= productList.get(ch).price ) {
                    productList.get(ch).push();
                    productList.get(ch).drop();
                }
                else{System.out.println("You did not entered enough money... Cannot give product... Here, take your money back.");}
            }


    }


}



    class Test{
    public static void main(String []args){
        Scanner scan = new Scanner(System.in);
        VendingMachine V1 = new VendingMachine();
        System.out.println("Please enter the slot of the product you want...");
        V1.custChoice(scan.nextInt());
    }
}