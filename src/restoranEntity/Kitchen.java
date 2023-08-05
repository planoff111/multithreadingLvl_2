package restoranEntity;

import dishes.Dish;
import dishes.States;
import employee.Cook;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class Kitchen {

    public Kitchen() {
    }

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public HashMap<String, Dish> getDish() {
        HashMap<String, Dish> listDish = new HashMap<>();
        listDish.put("паста", new Dish("Паста",
                List.of("Макарони", "Моцарела", "Соус Болоньєз"),
                List.of(States.BOLED, States.WITH_SAUCE, States.WITH_SPICES)));
        listDish.put("вареники", new Dish("Вареники",
                List.of("Тісто", "Начинка", "Масло"),
                List.of(States.BOLED, States.WITH_SAUCE, States.WITH_SPICES)));
        listDish.put("салат греческий", new Dish("Салат Греческий",
                List.of("Оливки", "Фета", "Салат", "Оливкова олія"),
                List.of(States.CHOPPED, States.WITH_SAUCE, States.WITH_SPICES)));
        listDish.put("борщ", new Dish("Борщ",
                List.of("Вода", "Мясо", "Буряк", "Морква", "Картопля", "Зажарка"),
                List.of(States.BOLED, States.CHOPPED, States.WITH_SPICES)));
        listDish.put("уха", new Dish("Уха",
                List.of("Риба", "Вода", "Картопля"),
                List.of(States.BOLED, States.CHOPPED, States.WITH_SAUCE)));
        listDish.put("різотто", new Dish("Різотто",
                List.of("Рис", "Соуc для Різотто", "Сир", "Вода"),
                List.of(States.BOLED, States.CHOPPED, States.WITH_SAUCE)));
        listDish.put("салат від шефа", new Dish("Салат від Шефа",
                List.of("Секретні інгрідієнти", "Секретний соус"),
                List.of(States.CHOPPED, States.WITH_SAUCE, States.WITH_SPICES)));
        listDish.put("картопля фрі", new Dish("Картопля Фрі",
                List.of("Картопля", "Олія", "Кетчуп фірмовий"),
                List.of(States.FRIED, States.CHOPPED, States.WITH_SAUCE)));
        return listDish;
    }

    public synchronized HashSet<Dish> filterOrder(List<String> orders, HashMap<String, Dish> dishes) {
        List<Dish> validDishes = new ArrayList<>();
        for (String order : orders) {
            List<Dish> filteredDishes = dishes.entrySet()
                    .stream()
                    .filter(dish -> dish.getKey().equals(order))
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
            validDishes.addAll(filteredDishes);
        }
        return new HashSet<>(validDishes);
    }


    public void startCook(HashSet<Dish> finalOrder) throws InterruptedException {
        Stove stove = new Stove(2);
        Table table = new Table(4);
        Lock lock = new ReentrantLock();
        int limit = finalOrder.size();
        Deque<Dish> queOfDish = new ArrayDeque<>(finalOrder);

        for (Dish dish : queOfDish) {
            Queue<Cook> cooks = new ArrayDeque<>();
            cooks.add(new Cook("Петро", stove, dish, lock, table));
            cooks.add(new Cook("Євген", stove, dish, lock, table));
            cooks.add(new Cook("Вахтанг", stove, dish, lock, table));
            cooks.add(new Cook("Іван", stove, dish, lock, table));
            Queue<Cook> reqForCook = cooks.stream()
                    .limit(limit).collect(Collectors.toCollection(ArrayDeque::new));
            while (!queOfDish.isEmpty() && !reqForCook.isEmpty()) {
                Cook corruntCook = reqForCook.poll();
                corruntCook.setDish(queOfDish.poll());
                if (dish.getStates().contains(States.FRIED) || dish.getStates().contains(States.BOLED)) {
                    Thread thread = new Thread(corruntCook);
                    thread.start();
                    thread.join();

                }
                if (dish.getStates().contains(States.CHOPPED)
                        || dish.getStates().contains(States.WITH_SAUCE)
                        || dish.getStates().contains(States.WITH_SPICES)) {
                    Thread thread = new Thread(corruntCook);
                    thread.start();
                    thread.join();

                }
            }
        }
    }
}

