package tasks;

public class Task2 {

    public static void run() {
        Detal[] masiv = new Detal[4];

        masiv[0] = new Detal("Gear", "Steel");
        masiv[1] = new Mekhanizm("Reducer", "Cast iron", 5);
        masiv[2] = new Product("Microscope", "Glass/metal", "Leica");
        masiv[3] = new Node("Piston assembly", "Aluminum", "Energy transfer");

        System.out.println(" Object Information ");
        for (Detal d : masiv) {
            d.Show();
        }

        System.out.println("\n Object Behavior (AI) ");

        BaseAI[] aiList = {
                new DetalAI(masiv[0]),
                new MekhanizmAI((Mekhanizm) masiv[1]),
                new ProductAI((Product) masiv[2]),
                new NodeAI((Node) masiv[3])
        };

        for (BaseAI ai : aiList) {
            ai.performAction();
        }
    }
}

class Detal {
    protected String name;
    protected String material;

    public Detal(String name, String material) {
        this.name = name;
        this.material = material;
    }

    public void Show() {
        System.out.println("Detal: " + name + ", material: " + material);
    }
}

class Mekhanizm extends Detal {
    private int numberOfParts;

    public Mekhanizm(String name, String material, int numberOfParts) {
        super(name, material);
        this.numberOfParts = numberOfParts;
    }

    public int getNumberOfParts() {
        return numberOfParts;
    }

    @Override
    public void Show() {
        System.out.println("Mekhanizm: " + name + ", material: " + material +
                ", number of parts: " + numberOfParts);
    }
}

class Product extends Detal {
    private String manufacturer;

    public Product(String name, String material, String manufacturer) {
        super(name, material);
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public void Show() {
        System.out.println("Product: " + name + ", material: " + material +
                ", manufacturer: " + manufacturer);
    }
}

class Node extends Detal {
    private String function;

    public Node(String name, String material, String function) {
        super(name, material);
        this.function = function;
    }

    public String getFunction() {
        return function;
    }

    @Override
    public void Show() {
        System.out.println("Node: " + name + ", material: " + material +
                ", function: " + function);
    }
}

abstract class BaseAI {
    protected Detal detal;

    public BaseAI(Detal detal) {
        this.detal = detal;
    }

    public abstract void performAction();
}

class DetalAI extends BaseAI {
    public DetalAI(Detal detal) {
        super(detal);
    }

    @Override
    public void performAction() {
        System.out.println(detal.name + " (basic detail) is being polished to improve surface quality.");
    }
}

class MekhanizmAI extends BaseAI {
    public MekhanizmAI(Mekhanizm detal) {
        super(detal);
    }

    @Override
    public void performAction() {
        Mekhanizm m = (Mekhanizm) detal;
        System.out.println(m.name + " mechanism with " + m.getNumberOfParts() +
                " parts is being assembled and tested for movement precision.");
    }
}

class ProductAI extends BaseAI {
    public ProductAI(Product detal) {
        super(detal);
    }

    @Override
    public void performAction() {
        Product p = (Product) detal;
        System.out.println(p.name + " product by " + p.getManufacturer() +
                " is undergoing quality control and packaging.");
    }
}

class NodeAI extends BaseAI {
    public NodeAI(Node detal) {
        super(detal);
    }

    @Override
    public void performAction() {
        Node n = (Node) detal;
        System.out.println(n.name + " node is performing function: " + n.getFunction() +
                ". Monitoring energy transfer efficiency.");
    }
}
