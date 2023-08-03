package employee;

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


    public Cook(String name, String position,List<String> order,Stove stove) {
        this.name = name;
        this.position = position;
        this.order = order;
        this.stove = stove;
    }





    @Override
    public void run() {
        try {
            if (!isReady){
                stove.tryToCook();
                kitchen.orderFinal(order,kitchen.getDish(),name);
                sleep(600);
                isReady = true;
                stove.releaseTheStove();
                sleep(600);
            }

       }catch (InterruptedException e){
            System.out.println(e);
        }
    }
}
