package employee;

import dishes.Dish;
import dishes.States;
import restoranEntity.Kitchen;
import restoranEntity.Stove;
import restoranEntity.Zal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cook extends Thread {
    private String name;
    private Stove stove;

    Kitchen kitchen = new Kitchen();
    Zal zal = new Zal();


    public Cook(String name, Stove stove) {
        this.name = name;
        this.stove = stove;

    }

    Dish dishToCook;


    public void addSpices() {
        System.out.println("I'm adding spices to " + dishToCook.getName());
    }

    public void addSause() {
        System.out.println("I'm adding sause to " + dishToCook.getName());
    }

    public void boil() {
        System.out.println("I'm boiling " + dishToCook.getName());
    }

    public void fry() {
        System.out.println("I'm frying " + dishToCook.getName());
    }


    public void chop() {
        System.out.println("I'm chopping " + dishToCook.getName());
    }

    public void startCook(List<Dish> finalOrder) throws InterruptedException {
        for (Dish dish : finalOrder) {
            if (dish.getStates().contains(States.FRIED) || finalOrder.contains(States.BOLED)) {
                useTheStove(dish);
            } else if (dish.getStates().contains(States.CHOPPED)) {
                chop();
            } else if (dish.getStates().contains(States.WITH_SAUCE)) {
                addSause();
            } else if (dish.getStates().contains(States.WITH_SPICES)) {
                addSpices();
            }
        }

    }

    private void useTheStove(Dish dish) throws InterruptedException {
        if (stove.getAvailiableSpots() > 0) {
            if (stove.tryToCook()) {
                if (dish.getStates().contains(States.FRIED)) {
                    fry();
                } else if (dish.getStates().contains(States.BOLED)) {
                    boil();
                    Thread.sleep(5000);
                }
                stove.releaseTheStove();
            }
        }
    }

    @Override
    public void run() {
        List<String> order = zal.getOrder();
        HashMap<String, Dish> dishes = kitchen.getDish();
        List<Dish> finalOrder = kitchen.finalOrder(kitchen.addSauseFilter(order, dishes),
                kitchen.addSpicesFilter(order, dishes),
                kitchen.boilFilter(order, dishes),
                kitchen.chopFilter(order, dishes),
                kitchen.fryFilter(order, dishes));

        try {
            startCook(finalOrder);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
