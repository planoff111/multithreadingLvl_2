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
        Stove stove = new Stove(2);
        Table table = new Table(4);
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
            Lock lock = new ReentrantLock();
            List<String> order = zal.getOrder();
            int limit = order.size();

            Queue<Cook> cooks = new ArrayDeque<>();
            cooks.add(new Cook("Петро", stove, order, lock,table));
            cooks.add(new Cook("Євген", stove, order, lock,table));
            cooks.add(new Cook("Вахтанг", stove, order, lock,table));
            cooks.add(new Cook("Іван", stove, order, lock,table));
            cooks.add(new Cook("Анна", stove, order, lock,table));

            Queue<Cook> reqForCook = cooks.stream()
                    .limit(limit).collect(Collectors.toCollection(ArrayDeque::new));



            List<Thread> threads = new ArrayList<>();
            for (Cook cook : reqForCook) {
                Thread thread = new Thread(cook);
                threads.add(thread);
                thread.start();

            }
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}