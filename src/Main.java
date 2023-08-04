import employee.Cook;
import dishes.Dish;
import restoranEntity.Kitchen;
import restoranEntity.Stove;
import restoranEntity.Zal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        chooseEdit(choose());


    }

    public static int choose(){
        System.out.println("Виберіть вашу опцію " +
                "\n1 - Переглянути меню" +
                "\n2 - Зробити замовлення");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.println("Введено недійсне значення. Будь ласка, введіть 1 або 2.");
        }
        int choose = scanner.nextInt();

        return choose;
    }

    public static void chooseEdit(int choose) {
        Kitchen kitchen = new Kitchen();
        Zal zal = new Zal();
        if (choose == 1){
            System.out.println("Menu : ");
        List<Dish> menu = new ArrayList<>(kitchen.getDish()
                .values());
        for (Dish dish:menu){
            System.out.println(dish.getName());
        }
            chooseEdit(choose());
        } else if (choose == 2) {
            Stove stove = new Stove(2);
            List<Cook> cooks = new ArrayList<>();

            cooks.add(new Cook("Петро", stove));
            cooks.add(new Cook("Євген", stove));
            cooks.add(new Cook("Вахтанг", stove));
            cooks.add(new Cook("Іван", stove));



        }


    }
}