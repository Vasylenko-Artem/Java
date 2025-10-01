package tasks;

// Написати додаток,який підраховує кількість символів у кожному текстовому файлі.
// Символи пробілу,повернення каретки,переходу на новий рядок і табуляції можуть
// розташовуватися в тексті в будь-якому місці і в будь-якій кількості.
// Ці символи рахувати не треба.Вивести назву файлу і кількість символів на екран.

import static utils.Input.*;

import java.io.*;

public class Task2 {
    public static void run() {
        String filePath = readLine("Enter the path to the file: ");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int totalChars = 0;

            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    if (!Character.isWhitespace(c)) {
                        totalChars++;
                    }
                }
            }

            System.out.println("File name: " + filePath);
            System.out.println("Number of characters (excluding whitespace): " + totalChars);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
        }
    }
}
