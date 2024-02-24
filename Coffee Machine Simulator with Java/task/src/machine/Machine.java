package machine;

import javax.crypto.Mac;
import java.util.Scanner;

public class Machine {

    private final CupOfCoffee cupOfEspresso;
    private final CupOfCoffee cupOfLatte;
    private final CupOfCoffee cupOfCappucino;
    private int water;
    private int milk;
    private int coffeeBeans;
    private int disposableCups;
    private int money;

    public Machine(){
        this.cupOfCappucino = new CupOfCoffee(200,100,12,6);
        this.cupOfEspresso = new CupOfCoffee(250,0,16,4);
        this.cupOfLatte = new CupOfCoffee(350,75,20,7);
        this.coffeeBeans = 120;
        this.milk = 540;
        this.water = 400;
        this.disposableCups = 9;
        this.money = 550;

    }
    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }

    public void setCoffeeBeans(int coffeeBeans) {
        this.coffeeBeans = coffeeBeans;
    }

    public int getDisposableCups(){
        return this.disposableCups;
    }

    public void setDisposableCups(int disposableCups){
        this.disposableCups = disposableCups;
    }
    public void setMoney(int money){
        this.money = money;
    }
    public int getMoney(){
        return this.money;
    }

    public void printContent(){
        System.out.print("The coffee machine has:\n" +
                getWater() + " ml of water\n" +
                getMilk() +  " ml of milk\n" +
                getCoffeeBeans() + " g of coffee beans\n" +
                getDisposableCups() + " disposable cups\n" +
                "$" + getMoney() + " of money\n");
    }
    public void giveMoney(){
        System.out.println("I gave you $" + getMoney());
        setMoney(0);
    }
    public void coffeeChoice(int choice){

        if(disposableCups <= 0){
            System.out.println("I don't have enough resources\n");
            return;
        }
        if(choice == 1){
            makeCoffee(cupOfEspresso);
        }
        if(choice == 2){
            makeCoffee(cupOfLatte);
        }

        if(choice == 3){
            makeCoffee(cupOfCappucino);
        }
    }

    public void makeCoffee(CupOfCoffee cupOfCoffee){

        if(getWater() < cupOfCoffee.getWater()){
            System.out.println("Sorry, not enough water!");
        } else if(getMilk() < cupOfCoffee.getMilk()){
            System.out.println("Sorry, not enough milk!");
        } else if(getCoffeeBeans() < cupOfCoffee.getCoffeeBeans()){
            System.out.println("Sorry, not enough coffee beans!");
        } else {
            setWater(getWater() - cupOfCoffee.getWater());
            setCoffeeBeans(getCoffeeBeans() - cupOfCoffee.getCoffeeBeans());
            setMilk(getMilk() - cupOfCoffee.getMilk());
            setMoney(getMoney() + cupOfCoffee.getPrice());
            setDisposableCups(getDisposableCups() - 1);
        }
    }

    public void menu(){
        Scanner scanner = new Scanner(System.in);
        //--
        while(true){
            System.out.println("Write action (buy, fill, take, remaining, exit): \n");
            String input = scanner.nextLine().toLowerCase();

            if(input.equals("buy")){
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: \n");
                input = scanner.nextLine();
                if(input.toLowerCase().equals("back")){
                    continue;
                } else {
                    coffeeChoice(errorHandling(input));
                }

            }

            if(input.equals("fill")){
                // --
                System.out.println("Write how many ml of water you want to add: \n");
                input = scanner.nextLine();
                setWater(getWater() + errorHandling(input));

                // --
                System.out.println("Write how many ml of milk you want to add: \n");
                input = scanner.nextLine();
                setMilk(getMilk() + errorHandling(input));

                //--
                System.out.println("Write how many grams of coffee beans you want to add: \n");
                input = scanner.nextLine();
                setCoffeeBeans( getCoffeeBeans() + errorHandling(input));

                //--
                System.out.println("Write how many disposable cups you want to add: \n");
                input = scanner.nextLine();
                setDisposableCups(getDisposableCups() + errorHandling(input));
            }

            if(input.equals("take")){
                giveMoney();
            }

            if(input.equals("remaining")){
                printContent();
            }
            if(input.equals("exit")){
                System.exit(0);
            }

        }

    }
    public static int errorHandling(String s){
        try{
            return Integer.parseInt(s);
        } catch (NumberFormatException e){
            System.out.println(e);
        }
        return -1;
    }

}
