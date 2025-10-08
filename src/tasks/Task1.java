package tasks;

import static utils.Input.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

import series.*;

public class Task1 {
    public static void run() {
        try {
            ArrayList<Linear> linears = readLinears("test/linear.txt");
            ArrayList<Exponential> exponentials = readExponentials("test/exponential.txt");

            System.out.println("Read from files");
            printList(linears);
            printList(exponentials);

            // Sort each list
            Collections.sort(linears);
            Collections.sort(exponentials);

            System.out.println("\nAfter sorting");
            printList(linears);
            printList(exponentials);

            // Add new records from keyboard
            System.out.print("\nEnter Linear (first ratio): ");
            linears.add(new Linear(readDouble(), readDouble()));

            System.out.print("Enter Exponential (first ratio): ");
            exponentials.add(new Exponential(readDouble(), readDouble()));

            // Sort again
            Collections.sort(linears);
            Collections.sort(exponentials);

            System.out.println("\nAfter adding new and sorting again ");
            printList(linears);
            printList(exponentials);

            // Combine into one ArrayList<Series>
            ArrayList<Series> all = new ArrayList<>();
            all.addAll(linears);
            all.addAll(exponentials);

            // Sort with Comparator
            Collections.sort(all, new SeriesComparator());

            System.out.println("\nCombined and sorted (SeriesComparator) ");
            printList(all);

            // Save to file
            try (PrintWriter out = new PrintWriter("test/all_series.txt")) {
                for (Series s : all) {
                    out.println(s);
                }
            }

            System.out.println("\nSaved to test/all_series.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Linear> readLinears(String filename) throws IOException {
        ArrayList<Linear> list = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextDouble()) {
                double first = sc.nextDouble();
                double ratio = sc.nextDouble();
                list.add(new Linear(first, ratio));
            }
        }
        return list;
    }

    private static ArrayList<Exponential> readExponentials(String filename) throws IOException {
        ArrayList<Exponential> list = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextDouble()) {
                double first = sc.nextDouble();
                double ratio = sc.nextDouble();
                list.add(new Exponential(first, ratio));
            }
        }
        return list;
    }

    private static void printList(Collection<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }
}
