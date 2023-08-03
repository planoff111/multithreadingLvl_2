package restoranEntity;

import dishes.Dish;
import dishes.States;
import employee.Cook;

import java.util.*;
import java.util.stream.Collectors;

public class Kitchen {
    Cook cook;
    Stove stove = new Stove(2);


    public HashMap<String, Dish> getDish() {
        HashMap<String, Dish> listDish = new HashMap<>();
        listDish.put("паста", new Dish("Паста",
                List.of("Макарони", "Моцарела", "Соус Болоньєз"),
                List.of(States.BOLED,States.CHOPPED,States.WITH_SAUCE,States.WITH_SPICES)));
        listDish.put("вареники", new Dish("Вареники",
                List.of("Тісто", "Начинка", "Масло"),
                List.of(States.BOLED,States.WITH_SAUCE,States.WITH_SPICES)));
        listDish.put("салат греческий", new Dish("Салат Греческий",
                List.of("Оливки", "Фета", "Салат", "Оливкова олія"),
                List.of(States.CHOPPED,States.WITH_SAUCE)));
        listDish.put("борщ", new Dish("Борщ",
                List.of("Вода", "Мясо", "Буряк", "Морква", "Картопля", "Зажарка"),
                List.of(States.BOLED,States.CHOPPED,States.WITH_SAUCE,States.WITH_SPICES)));
        listDish.put("уха", new Dish("Уха",
                List.of("Риба", "Вода", "Картопля"),
                List.of(States.BOLED,States.CHOPPED,States.WITH_SAUCE)));
        listDish.put("різотто", new Dish("Різотто",
                List.of("Рис", "Соуc для Різотто", "Сир", "Вода"),
                List.of(States.BOLED,States.CHOPPED,States.WITH_SAUCE)));
        listDish.put("салат від шефа", new Dish("Салат від Шефа",
                List.of("Секретні інгрідієнти", "Секретний соус"),
                List.of(States.CHOPPED,States.WITH_SAUCE,States.WITH_SPICES)));
        listDish.put("картопля фрі", new Dish("Картопля Фрі",
                List.of("Картопля", "Олія", "Кетчуп фірмовий"),
                List.of(States.FRIED,States.CHOPPED,States.WITH_SAUCE,States.WITH_SPICES)));


        return listDish;
    }


    public List<String> orderFinal(List<String> orders, HashMap<String, Dish> dishes, String name) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (String order : orders) {
            if (order.equals(dishes.keySet())){
                if (dishes.values().equals(States.FRIED)){
                    stove.tryToCook();
                    cook.fry();
                    stove.releaseTheStove();
                }
                if (dishes.values().equals(States.CHOPPED)){
                    stove.tryToCook();
                    cook.chop();
                    stove.releaseTheStove();
                }
                if (dishes.values().equals(States.BOLED)){
                    stove.tryToCook();
                    cook.boil();
                    stove.releaseTheStove();
                }
                if (dishes.values().equals(States.WITH_SAUCE)){
                    stove.tryToCook();
                    cook.addSause();
                    stove.releaseTheStove();
                }
                if (dishes.values().equals(States.WITH_SPICES)){
                    stove.tryToCook();
                    cook.addSpices();
                    stove.releaseTheStove();
                }
                list.add(order);
            }

        }
        return list;
    }
}
