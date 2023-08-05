package employee;

import dishes.Dish;
import dishes.States;
import restoranEntity.Kitchen;
import restoranEntity.Stove;
import restoranEntity.Table;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

public class Cook extends Thread {
    private String name;
    private Stove stove;
    volatile List<String> order;
    private final Lock lock;
    private Table table;

    public Cook(String name, Stove stove, List<String> order, Lock lock, Table table) {
        this.name = name;
        this.stove = stove;
        this.order = order;
        this.lock = lock;
        this.table = table;

    }

    public void addSpices(Dish dish) {
        System.out.println("Cook " + name + " adding spices to " + dish.getName());
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
        System.out.println("Cook " + name + " chopping ingridients for " + dish.getName());
    }


    public void startCook(List<Dish> finalOrder) throws InterruptedException {

            for (Dish dish: finalOrder){
            if (dish.getStates().contains(States.FRIED) || dish.getStates().contains(States.BOLED)) {
                useTheStove(dish);
            }
            if (dish.getStates().contains(States.CHOPPED)
                    || dish.getStates().contains(States.WITH_SAUCE)
                    || dish.getStates().contains(States.WITH_SPICES)){
                useTheTable(dish);
            }

        }






    }

    private synchronized void useTheStove(Dish dish) throws InterruptedException {
        if (stove.getAvailiableSpots() > 0) {
            if (stove.tryToCook()) {
                if (dish.getStates().contains(States.FRIED)) {
                    fry(dish);
                } else if (dish.getStates().contains(States.BOLED)) {
                    boil(dish);
                }
                Thread.sleep(1000);

            }
        }
    }

    private synchronized void useTheTable(Dish dish) throws InterruptedException {
        if (table.getAvailiableSpots() > 0) {
            if (table.tryToUseTable()) {
                if (dish.getStates().contains(States.CHOPPED)) {
                    chop(dish);
                }
                if (dish.getStates().contains(States.WITH_SPICES)) {
                    addSpices(dish);
                }
                if (dish.getStates().contains(States.WITH_SAUCE)) {
                    addSause(dish);
                }
                Thread.sleep(1000);
            }
        }
    }

    @Override
    public void run() {
        System.out.println();
        Kitchen kitchen = new Kitchen();
        try {
            startCook(kitchen.finalOrder(
                    kitchen.addSauseFilter(order, kitchen.getDish()),
                    kitchen.addSpicesFilter(order, kitchen.getDish()),
                    kitchen.boilFilter(order, kitchen.getDish()),
                    kitchen.chopFilter(order, kitchen.getDish()),
                    kitchen.fryFilter(order, kitchen.getDish())));
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

}
