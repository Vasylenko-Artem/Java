package tasks;

// З файлу,назва якого вводиться користувачем,прочитати дані в один із стандартних контейнерів.
// Обробити дані згідно варіанту завдання і записати результати у інший файл.
/// Кожен рядок задає ціле або дробове число(якщо рядок містить лише цифри то вважати його цілим
// у іншому випадку дробовим,якщо числа не правильний то цей рядок відкидається),порахувати суму дробових чисел,
// добуток усіх цілих.Вивести відсортовані цілі та дробові числа в файли задані користувачем.
// Суму і добуток вивести на консоль.

import static utils.Input.*;

import java.io.*;
import java.util.*;

public class Task1 {
    public static void run() {
        try {
            // Creating containers
            List<Integer> integers = new ArrayList<>();
            List<Double> doubles = new ArrayList<>();

            String inputFile = readLine("Input the name of the file: ");

            // Reading from file
            try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty())
                        continue;

                    String[] tokens = line.split("\\s+");

                    for (String token : tokens) {
                        if (isInteger(token)) {
                            integers.add(Integer.parseInt(token));
                        } else if (isDouble(token)) {
                            doubles.add(Double.parseDouble(token.replace(',', '.')));
                        }
                    }
                }
            }

            // Processing
            long product = calculateProduct(integers);
            double sum = calculateSum(doubles);

            // Sort
            Collections.sort(integers);
            Collections.sort(doubles);

            String intFile = readLine("Input the name of the integer file: ");
            String doubleFile = readLine("Input the name of the double file: ");

            // Write to files
            writeListToFile(integers, intFile);
            writeListToFile(doubles, doubleFile);

            // Output result
            System.out.println("The amount of fractional: " + sum);
            System.out.println("The product of integers: " + product);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Error when working with files: " + e.getMessage());
        }
    }

    private static boolean isInteger(String s) {
        return s.matches("-?\\d+");
    }

    private static boolean isDouble(String s) {
        return s.matches("-?\\d+[.,]\\d+");
    }

    private static long calculateProduct(List<Integer> integers) {
        if (integers.isEmpty())
            return 0;
        long product = 1;
        for (int val : integers)
            product *= val;
        return product;
    }

    private static double calculateSum(List<Double> doubles) {
        if (doubles.isEmpty())
            return 0;
        double sum = 0.0;
        for (double val : doubles)
            sum += val;
        return sum;
    }

    private static <T> void writeListToFile(List<T> list, String fileName) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (T val : list)
                pw.println(val);
        }
    }
}
