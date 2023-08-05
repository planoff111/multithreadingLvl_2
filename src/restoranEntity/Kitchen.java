package restoranEntity;

import dishes.Dish;
import dishes.States;
import employee.Cook;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class Kitchen {

    public Kitchen() {
    }







    public HashMap<String, Dish> getDish() {
        HashMap<String, Dish> listDish = new HashMap<>();
        listDish.put("паста", new Dish("Паста",
                List.of("Макарони", "Моцарела", "Соус Болоньєз"),
                List.of(States.BOLED, States.WITH_SAUCE)));
        listDish.put("вареники", new Dish("Вареники",
                List.of("Тісто", "Начинка", "Масло"),
                List.of(States.BOLED, States.WITH_SAUCE, States.WITH_SPICES)));
        listDish.put("салат греческий", new Dish("Салат Греческий",
                List.of("Оливки", "Фета", "Салат", "Оливкова олія"),
                List.of(States.CHOPPED, States.WITH_SAUCE)));
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

    public List<Dish> addSpicesFilter(List<String> orders, HashMap<String, Dish> dishes) {
        List<Dish> validDishes = new ArrayList<>();
        for (String order : orders) {
            List<Dish> filteredDishes = dishes.entrySet()
                    .stream()
                    .filter(dish -> dish.getKey().equals(order))
                    .filter(dish -> dish.getValue().getStates().contains(States.WITH_SPICES))
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
            validDishes.addAll(filteredDishes);
        }

        return validDishes;
    }

    public List<Dish> addSauseFilter(List<String> orders, HashMap<String, Dish> dishes) {
        List<Dish> validDishes = new ArrayList<>();
        for (String order : orders) {
            List<Dish> filteredDishes = dishes.entrySet()
                    .stream()
                    .filter(dish -> dish.getKey().equals(order))
                    .filter(dish -> dish.getValue().getStates().equals(States.WITH_SAUCE))
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
            validDishes.addAll(filteredDishes);
            System.out.println(validDishes);
        }
        return validDishes;
    }

    public List<Dish> boilFilter(List<String> orders, HashMap<String, Dish> dishes) {
        List<Dish> validDishes = new ArrayList<>();
        for (String order : orders) {
            List<Dish> filteredDishes = dishes.entrySet()
                    .stream()
                    .filter(dish -> dish.getKey().equals(order))
                    .filter(dish -> dish.getValue().getStates().equals(States.BOLED))
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
            validDishes.addAll(filteredDishes);
        }
        System.out.println(validDishes);

        return validDishes;
    }

    public List<Dish> fryFilter(List<String> orders, HashMap<String, Dish> dishes) {
        List<Dish> validDishes = new ArrayList<>();
        for (String order : orders) {
            List<Dish> filteredDishes = dishes.entrySet()
                    .stream()
                    .filter(dish -> dish.getKey().equals(order))
                    .filter(dish -> dish.getValue().getStates().equals(States.FRIED))
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
            validDishes.addAll(filteredDishes);
        }

        return validDishes;
    }


    public List<Dish> chopFilter(List<String> orders, HashMap<String, Dish> dishes) {
        List<Dish> validDishes = new ArrayList<>();
        for (String order : orders) {
            List<Dish> filteredDishes = dishes.entrySet()
                    .stream()
                    .filter(dish -> dish.getKey().equals(order))
                    .filter(dish -> dish.getValue().getStates().equals(States.CHOPPED))
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
            validDishes.addAll(filteredDishes);
        }

        return validDishes;

    }

    public List<Dish> finalOrder(List<Dish> chop, List<Dish> fry, List<Dish> boil, List<Dish> sause, List<Dish> spices) throws InterruptedException {
        List<Dish> finalOrder = new ArrayList<>();

            finalOrder.addAll(chop);
            finalOrder.addAll(fry);
            finalOrder.addAll(boil);
            finalOrder.addAll(sause);
            finalOrder.addAll(spices);

        System.out.println(finalOrder);

        return finalOrder;
    }




}

