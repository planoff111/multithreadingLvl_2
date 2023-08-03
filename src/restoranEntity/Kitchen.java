package restoranEntity;

import dishes.Dish;
import dishes.States;
import employee.Cook;

import java.util.*;

public class Kitchen {
    Cook cook;


    public HashMap<String, Dish> getDish() {
        HashMap<String, Dish> listDish = new HashMap<>();
        listDish.put("паста", new Dish("Паста",
                List.of("Макарони", "Моцарела", "Соус Болоньєз"),
                States.BOLED));
        listDish.put("вареники", new Dish("Вареники",
                List.of("Тісто", "Начинка", "Масло"), States.BOLED));
        listDish.put("салат греческий", new Dish("Салат Греческий",
                List.of("Оливки", "Фета", "Салат", "Оливкова олія"),
                States.CHOPPED));
        listDish.put("борщ", new Dish("Борщ",
                List.of("Вода", "Мясо", "Буряк", "Морква", "Картопля", "Зажарка"),
                States.BOLED));
        listDish.put("уха", new Dish("Уха",
                List.of("Риба", "Вода", "Картопля"),
                States.BOLED));
        listDish.put("різотто", new Dish("Різотто",
                List.of("Рис", "Соуc для Різотто", "Сир", "Вода"),
                States.BOLED));
        listDish.put("салат від шефа", new Dish("Салат від Шефа",
                List.of("Секретні інгрідієнти", "Секретний соус"),
                States.CHOPPED));
        listDish.put("картопля фрі", new Dish("Картопля Фрі",
                List.of("Картопля", "Олія", "Кетчуп фірмовий"),
                States.FRIED));


        return listDish;
    }



    public List<String> orderFinal(List<String> orders, HashMap<String, Dish> dishes, String name) {
        List<String> list = new ArrayList<>();
        for (String order : orders) {
            String item = order;
            dishes.entrySet()
                    .stream()
                    .filter(dish -> dish.getKey().equals(item))
                    .filter(dish -> dish.getValue().getStates().equals(States.CHOPPED))
                    .forEach(dish-> System.out.println("Нарізається поварем " + name + " Страва " + dishes.get(item).getName()));

            dishes.entrySet()
                    .stream()
                    .filter(dish -> dish.getKey().equals(item))
                    .filter(dish -> dish.getValue().getStates().equals(States.FRIED))
                    .forEach(dish-> System.out.println("Жариться поварем " +  name + " Страва " + dishes.get(item).getName()));

            dishes.entrySet()
                    .stream()
                    .filter(dish -> dish.getKey().equals(item))
                    .filter(dish -> dish.getValue().getStates().equals(States.BOLED))
                    .forEach(dish-> System.out.println("Вариться поварем "+ name + " Страва " + dishes.get(item).getName()));
            list.add(item);
        }
        return list;
    }


}

