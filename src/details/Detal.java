package details;

// Superclass
public class Detal {
    protected String name; // Name of details
    protected String material; // Material

    public Detal(String name, String material) {
        this.name = name;
        this.material = material;
    }

    public void Show() {
        System.out.println("Detal: " + name + ", material: " + material);
    }
}
