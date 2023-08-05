import dishes.States;
import employee.Cook;
import dishes.Dish;
import restoranEntity.Kitchen;
import restoranEntity.Stove;
import restoranEntity.Table;
import restoranEntity.Zal;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;


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
            kitchen.startCook(kitchen.filterOrder(order, kitchen.getDish()));




        }
    }
}