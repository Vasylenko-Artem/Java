package details;

public class Node extends Detal {
    private String function; // Functional purpose

    public Node(String name, String material, String function) {
        super(name, material);
        this.function = function;
    }

    @Override
    public void Show() {
        System.out.println("Node: " + name + ", material: " + material +
                ", function: " + function);
    }
}
