package tasks;

import details.Detal;
import details.Mekhanizm;
import details.Node;
import details.Product;

public class Task1 {
    public static void run() {
        Detal[] masiv = new Detal[4];

        masiv[0] = new Detal("Gear", "Steel");
        masiv[1] = new Mekhanizm("Reducer", "Cast iron", 5);
        masiv[2] = new Product("Microscope", "Glass/metal", "Leica");
        masiv[3] = new Node("Piston assembly", "Aluminum", "Energy transfer");

        for (Detal d : masiv) {
            d.Show();
        }
    }
}
