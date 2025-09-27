package details;

public class Product extends Detal {
    private String manufacturer; // Manufacturer

    public Product(String name, String material, String manufacturer) {
        super(name, material);
        this.manufacturer = manufacturer;
    }

    @Override
    public void Show() {
        System.out.println("Product: " + name + ", material: " + material +
                ", manufacturer: " + manufacturer);
    }
}
