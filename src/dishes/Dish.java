package dishes;

import java.util.List;

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
}
