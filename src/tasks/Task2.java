package tasks;

import utils.window.FlexibleFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task2 {

    public static void run() {
        SwingUtilities.invokeLater(Task2Frame::new);
    }
}

// Власне виключення
class CustomArithmeticException extends ArithmeticException {
    public CustomArithmeticException(String message) {
        super(message);
    }
}

// Основне вікно
class Task2Frame extends JFrame {
    private JTextField fileField;
    private JTable matrixTable;
    private JTable resultTable;
    private JLabel statusLabel;

    public Task2Frame() {
        FlexibleFrame frame = new FlexibleFrame();
        frame.setTitle("Task 2");

        // Цвета
        Color darkBg = new Color(13, 13, 13);
        Color orange = new Color(255, 107, 0);
        Color textColor = Color.WHITE;

        frame.setBackground(darkBg);

        // Панель верхняя
        JPanel topPanel = new JPanel();
        topPanel.setBackground(darkBg);

        JLabel fileLabel = new JLabel("Файл:");
        fileLabel.setForeground(orange);
        topPanel.add(fileLabel);

        fileField = new JTextField("test/matrix.txt", 20);
        fileField.setBackground(darkBg);
        fileField.setForeground(textColor);
        fileField.setCaretColor(textColor);
        topPanel.add(fileField);

        JButton loadButton = new JButton("Завантажити");
        loadButton.setBackground(orange);
        loadButton.setForeground(Color.BLACK);
        topPanel.add(loadButton);

        // Таблицы
        matrixTable = new JTable();
        matrixTable.setBackground(darkBg);
        matrixTable.setForeground(textColor);
        matrixTable.setGridColor(orange);

        resultTable = new JTable();
        resultTable.setBackground(darkBg);
        resultTable.setForeground(textColor);
        resultTable.setGridColor(orange);

        JPanel tablePanel = new JPanel(new GridLayout(2, 1, 5, 5));
        tablePanel.add(new JScrollPane(matrixTable));
        tablePanel.add(new JScrollPane(resultTable));
        tablePanel.setBackground(darkBg);

        // Статус
        statusLabel = new JLabel("Готово");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setForeground(orange);
        statusLabel.setBackground(darkBg);
        statusLabel.setOpaque(true);

        // Обработчик кнопки
        loadButton.addActionListener(e -> loadMatrix());

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);

    }

    private void loadMatrix() {
        String filename = fileField.getText().trim();

        try {
            List<double[]> rows = new ArrayList<>();
            try (Scanner scanner = new Scanner(new File(filename))) {
                while (scanner.hasNextLine()) {
                    String[] parts = scanner.nextLine().trim().split("\\s+");
                    double[] row = new double[parts.length];
                    for (int i = 0; i < parts.length; i++) {
                        row[i] = Double.parseDouble(parts[i]); // може викликати NumberFormatException
                    }
                    rows.add(row);
                }
            }

            int n = rows.size();
            if (n == 0)
                throw new CustomArithmeticException("Матриця порожня!");
            if (n > 15)
                throw new CustomArithmeticException("Розмір матриці перевищує 15!");

            double[][] matrix = rows.toArray(new double[0][]);
            boolean[] logicVector = computeLogicVector(matrix);

            // Виводимо у таблиці
            showMatrix(matrix, logicVector);

            statusLabel.setText("Матриця успішно завантажена ✅");

        } catch (FileNotFoundException e) {
            statusLabel.setText("Помилка: файл не знайдено ❌");
        } catch (NumberFormatException e) {
            statusLabel.setText("Помилка: невірний формат даних ❌");
        } catch (CustomArithmeticException e) {
            statusLabel.setText("Помилка: " + e.getMessage());
        } catch (Exception e) {
            statusLabel.setText("Невідома помилка: " + e.getMessage());
        }
    }

    private boolean[] computeLogicVector(double[][] matrix) throws CustomArithmeticException {
        int n = matrix.length;
        boolean[] L = new boolean[n];

        for (int i = 0; i < n; i++) {
            int pos = 0, neg = 0;
            for (double v : matrix[i]) {
                if (v > 0)
                    pos++;
                else if (v < 0)
                    neg++;
            }

            // Наприклад: генеруємо власне виключення, якщо рядок містить тільки нулі
            if (pos == 0 && neg == 0) {
                throw new CustomArithmeticException("У рядку " + (i + 1) + " всі елементи = 0");
            }

            L[i] = neg > pos;
        }

        return L;
    }

    private void showMatrix(double[][] matrix, boolean[] logicVector) {
        int n = matrix.length;
        String[] columnNames = new String[n];
        for (int i = 0; i < n; i++)
            columnNames[i] = "X" + (i + 1);

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (double[] row : matrix) {
            Object[] objRow = new Object[row.length];
            for (int j = 0; j < row.length; j++)
                objRow[j] = row[j];
            model.addRow(objRow);
        }
        matrixTable.setModel(model);

        // Таблиця логічного вектора
        DefaultTableModel resultModel = new DefaultTableModel(new String[] { "L(i)" }, 0);
        for (boolean v : logicVector)
            resultModel.addRow(new Object[] { v });
        resultTable.setModel(resultModel);
    }
}
