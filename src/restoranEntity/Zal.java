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

    public List<String> getOrder() throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        List<String> order = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть ваше замовлення через кому без дублів і без пробілів і страви, які є в наявності!");
        String inputItems = scanner.nextLine().toLowerCase();
        String[] items = inputItems.split(",");
        semaphore.acquire();

        for (String item : items) {
            order.add(item);
        }

        return order;
    }


}
