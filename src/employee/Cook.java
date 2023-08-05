package employee;

import dishes.Dish;
import dishes.States;
import restoranEntity.Kitchen;
import restoranEntity.Stove;
import restoranEntity.Table;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Cook extends Thread {
    private final Object lock1 = new Object();
    private String name;
    private Stove stove;
    volatile Dish dish;
    private final Lock lock;
    private Table table;

    public Cook(String name, Stove stove, Dish dish, Lock lock, Table table) {
        this.name = name;
        this.stove = stove;
        this.dish = dish;
        this.lock = lock;
        this.table = table;

    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public  void addSpices(Dish dish) {
        System.out.println("Cook " + name + " adding spices to " + dish.getName());
    }

    public  void addSause(Dish dish) {
        System.out.println("Cook " + name + " adding sause to " + dish.getName());
    }

    public  void boil(Dish dish) {
        System.out.println("Cook " + name + " boiling " + dish.getName());
    }

    public  void fry(Dish dish) {
        System.out.println("Cook " + name + " frying " + dish.getName());
    }


    public  void chop(Dish dish) {
        System.out.println("Cook " + name + " chopping ingridients for " + dish.getName());
    }




    private  void useTheStove(Dish dish) throws InterruptedException {
        if (stove.getAvailiableSpots() > 0) {
            if (stove.tryToCook()) {
                if (dish.getStates().contains(States.FRIED)) {
                    fry(dish);
                } else if (dish.getStates().contains(States.BOLED)) {
                    boil(dish);
                }
                stove.releaseTheStove();
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

            } table.releaseTheTable();
            Thread.sleep(1000);
        }
    }

    @Override
    public void run() {
        try {
            useTheTable(dish);
            useTheStove(dish);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
