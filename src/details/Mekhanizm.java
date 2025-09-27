package details;

public class Mekhanizm extends Detal {
    private int numberOfParts; // Number of parts

    public Mekhanizm(String name, String material, int numberOfParts) {
        super(name, material);
        this.numberOfParts = numberOfParts;
    }

    @Override
    public void Show() {
        System.out.println("Mekhanizm: " + name + ", material: " + material +
                ", number of parts: " + numberOfParts);
    }
}
