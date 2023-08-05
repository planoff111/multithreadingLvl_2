package restoranEntity;

import customer.Customer;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Zal {

    public static void welcome() {
        Customer customer = new Customer();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Вітаємо вас в ресторані *Від Олександра*\n" +
                "Як можемо до вас звертатись? ");
        customer.setName(scanner.next());
        System.out.println("Дуже приємно " + customer.getName());
    }

    public synchronized List<String> getOrder()  {
        List<String> order = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть ваше замовлення через кому без дублів і без пробілів і страви, які є в наявності!");
        String inputItems = scanner.nextLine().toLowerCase();
        String[] items = inputItems.split(",");

        for (String item : items) {
            order.add(item);
        }
        return order;
    }


}
