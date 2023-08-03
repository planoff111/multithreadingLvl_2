package dishes;

import java.util.List;

public class Dish {
    public Dish() {
    }
    private String name;
    private List<String> ingridients;
    private States states;

    public Dish(String name, List<String> ingridients, States states) {
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

    public States getStates() {
        return states;
    }
}
