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
    List<String> order;



    public Cook() {
    }

    public Cook(String name, Stove stove, List<String> order) {
        this.name = name;
        this.stove = stove;
        this.order = order;
    }

    public void addSpices(Dish dish) {
        System.out.println("Cook " + name + " adding spices to " + dish.getName() );
    }

    public void addSause(Dish dish) {
        System.out.println("Cook " + name + " adding sause to " + dish.getName());
    }

    public void boil(Dish dish) {
        System.out.println("Cook " + name + " boiling " + dish.getName());
    }

    public void fry(Dish dish) {
        System.out.println("Cook " + name + " frying " + dish.getName());
    }


    public void chop(Dish dish) {
        System.out.println("Cook " + name + " chopping " + dish.getName() );
    }



    public void startCook(List<Dish> finalOrder) throws InterruptedException {
        for (Dish dish : finalOrder) {
            if (dish.getStates().contains(States.FRIED) || dish.getStates().contains(States.BOLED)) {
                useTheStove(dish);
            }
            if (dish.getStates().contains(States.CHOPPED)) {
                chop(dish);
            }
            if (dish.getStates().contains(States.WITH_SAUCE)) {
                addSause(dish);
            }
            if (dish.getStates().contains(States.WITH_SPICES)) {
                addSpices(dish);
            }
        }

    }

    private void useTheStove(Dish dish) throws InterruptedException {
        if (stove.getAvailiableSpots() > 0) {
            if (stove.tryToCook()) {
                if (dish.getStates().contains(States.FRIED)) {
                    fry(dish);
                } else if (dish.getStates().contains(States.BOLED)) {
                    boil(dish);
                    Thread.sleep(1000);
                }
                stove.releaseTheStove();
            }
        }
    }
    @Override
    public void run() {
        System.out.println();
        Kitchen kitchen = new Kitchen() ;
        try {
            startCook(kitchen.finalOrder(kitchen.addSauseFilter(order,kitchen.getDish()),
                    kitchen.addSpicesFilter(order,kitchen.getDish()),
                    kitchen.boilFilter(order,kitchen.getDish()),
                    kitchen.chopFilter(order,kitchen.getDish()),
                    kitchen.fryFilter(order,kitchen.getDish())));
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

}
