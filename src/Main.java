import employee.Cook;
import dishes.Dish;
import restoranEntity.Kitchen;
import restoranEntity.Stove;
import restoranEntity.Zal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws InterruptedException {

        Zal.welcome();
        chooseEdit(choose());


    }

    public static int choose() {
        System.out.println("Виберіть вашу опцію " +
                "\n1 - Переглянути меню" +
                "\n2 - Зробити замовлення");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Введено недійсне значення. Будь ласка, введіть 1 або 2.");
        }
        int choose = scanner.nextInt();

        return choose;
    }

    public static void chooseEdit(int choose) throws InterruptedException {
        Zal zal = new Zal();
        Stove stove = new Stove(2);
        Kitchen kitchen = new Kitchen();

        if (choose == 1) {
            System.out.println("Menu : ");
            List<Dish> menu = new ArrayList<>(kitchen.getDish()
                    .values());
            for (Dish dish : menu) {
                System.out.println(dish.getName());
            }
            chooseEdit(choose());
        } else if (choose == 2) {
           List<String> order = zal.getOrder();
            Cook cook = new Cook("Вахтанг",stove,order);
            Cook cook1 = new Cook("Ivan",stove,order);
            Cook cook2 = new Cook("Petro",stove,order);
            Cook cook3 = new Cook("Olia",stove,order);
            cook.start();
            cook1.start();
            cook2.start();
            cook3.start();


        }
    }
}