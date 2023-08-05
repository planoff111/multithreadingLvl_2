package dishes;

import java.util.List;
import java.util.Objects;

public class Dish {
    public Dish() {
    }
    private String name;
    private List<String> ingridients;
    private List<States> states;

    public Dish(String name, List<String> ingridients, List<States> states) {
        this.name = name;
        this.ingridients = ingridients;
        this.states = states;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngridients() {
        return ingridients;
    }

    public List<States> getStates() {
        return states;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", ingridients=" + ingridients +
                ", states=" + states +
                '}';
    }
}
