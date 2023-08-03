package employee;

import dishes.Dish;
import restoranEntity.Kitchen;
import restoranEntity.Stove;

import java.util.List;

public class Cook extends  Thread{
    Kitchen kitchen = new Kitchen();
    public List<String> order;
    public String name;
    public String position;
    Stove stove ;
    private boolean isReady = false;
    Dish dishToCook;


    public Cook(String name, String position,List<String> order,Stove stove) {
        this.name = name;
        this.position = position;
        this.order = order;
        this.stove = stove;
    }


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


    @Override
    public void run() {

        try {
            kitchen.orderFinal(order,kitchen.getDish(),name);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

}
