package tasks;

import app.Main;

import java.util.Scanner;

// abc acf bcdz hello zoo

public class Task4 {
    public static void run() {
        Scanner sc = Main.sc;

        System.out.println("Input text (lowercase latin letters, words separated by spaces/punctuation):");
        String text = sc.nextLine();

        // Split the text into words
        String[] words = text.split("[^a-z]+");

        System.out.println("Words with letters in alphabetical order:");
        for (String word : words) {
            if (!word.isEmpty() && isAlphabetical(word)) {
                System.out.println(word);
            }
        }
    }

    private static boolean isAlphabetical(String word) {
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) < word.charAt(i - 1)) {
                // if (word.charAt(i) != word.charAt(i - 1) + 1) {
                return false;
            }
        }
        return true;
    }

}
