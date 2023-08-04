package employee;

import dishes.Dish;
import dishes.States;
import restoranEntity.Kitchen;
import restoranEntity.Stove;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cook implements Runnable{
    public List<String> order;
    public String name;
    Stove stove ;
    private boolean isReady = false;
    Dish dishToCook;


    public Cook(String name,Stove stove) {
        this.name = name;
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

    public void bake() {
        System.out.println("I'm baking " + dishToCook.getName());
    }

    public void chop() {
        System.out.println("I'm chopping " + dishToCook.getName());
    }



    @Override
    public void run() {




    }

}
