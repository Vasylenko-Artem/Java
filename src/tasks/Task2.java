package tasks;

import utils.window.FlexibleFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task2 {
    public static void run() {
        SwingUtilities.invokeLater(Task2Frame::new);
    }
}

// Exclusion itself
class CustomArithmeticException extends ArithmeticException {
    public CustomArithmeticException(String message) {
        super(message);
    }
}

// Main window
class Task2Frame extends JFrame {
    private JTextField fileField;
    private JTextField sizeField;
    private JTable matrixTable;
    private JTable resultTable;
    private JLabel statusLabel;

    public Task2Frame() {
        FlexibleFrame frame = new FlexibleFrame("Task 2", 1000, 800, 1600, 1200, 1000, 800);
        frame.setTitle("Task 2");

        // Colors
        Color darkBg = new Color(13, 13, 13);
        Color orange = new Color(255, 107, 0);
        Color textColor = Color.WHITE;

        frame.setBackground(darkBg);

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(darkBg);

        JLabel fileLabel = new JLabel("File:");
        fileLabel.setForeground(orange);
        topPanel.add(fileLabel);

        fileField = new JTextField("test/matrix.txt", 15);
        fileField.setBackground(darkBg);
        fileField.setForeground(textColor);
        fileField.setCaretColor(textColor);
        topPanel.add(fileField);

        JButton loadButton = new JButton("Download");
        loadButton.setBackground(orange);
        loadButton.setForeground(Color.BLACK);
        topPanel.add(loadButton);

        JLabel sizeLabel = new JLabel("Size (n):");
        sizeLabel.setForeground(orange);
        topPanel.add(sizeLabel);

        sizeField = new JTextField("3", 3);
        sizeField.setBackground(darkBg);
        sizeField.setForeground(textColor);
        sizeField.setCaretColor(textColor);
        topPanel.add(sizeField);

        JButton createButton = new JButton("Create empty");
        createButton.setBackground(orange);
        createButton.setForeground(Color.BLACK);
        topPanel.add(createButton);

        JButton calcButton = new JButton("Calculate");
        calcButton.setBackground(orange);
        calcButton.setForeground(Color.BLACK);
        topPanel.add(calcButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBackground(orange);
        clearButton.setForeground(Color.BLACK);
        topPanel.add(clearButton);

        // Tables
        matrixTable = new JTable();
        matrixTable.setBackground(darkBg);
        matrixTable.setForeground(textColor);
        matrixTable.setGridColor(orange);

        resultTable = new JTable();
        resultTable.setBackground(darkBg);
        resultTable.setForeground(textColor);
        resultTable.setGridColor(orange);

        JScrollPane matrixScroll = new JScrollPane(matrixTable);
        matrixScroll.setBackground(darkBg);
        matrixScroll.getViewport().setBackground(darkBg);

        JScrollPane resultScroll = new JScrollPane(resultTable);
        resultScroll.setBackground(darkBg);
        resultScroll.getViewport().setBackground(darkBg);

        JPanel tablePanel = new JPanel(new GridLayout(2, 1, 5, 5));
        tablePanel.setBackground(darkBg);
        tablePanel.add(matrixScroll);
        tablePanel.add(resultScroll);

        // Status
        statusLabel = new JLabel("Done");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setForeground(orange);
        statusLabel.setBackground(darkBg);
        statusLabel.setOpaque(true);

        // Handlers
        loadButton.addActionListener(e -> loadMatrix());
        createButton.addActionListener(e -> createEmptyMatrix());
        calcButton.addActionListener(e -> computeFromTable());
        clearButton.addActionListener(e -> clearTables());

        // Adding everything to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
    }

    // Loading the matrix from a file
    private void loadMatrix() {
        String filename = fileField.getText().trim();

        try {
            List<double[]> rows = new ArrayList<>();
            try (Scanner scanner = new Scanner(new File(filename))) {
                while (scanner.hasNextLine()) {
                    String[] parts = scanner.nextLine().trim().split("\\s+");
                    double[] row = new double[parts.length];
                    for (int i = 0; i < parts.length; i++) {
                        row[i] = Double.parseDouble(parts[i]);
                    }
                    rows.add(row);
                }
            }

            int n = rows.size();
            if (n == 0)
                throw new CustomArithmeticException("Matrix is empty!");
            if (n > 15)
                throw new CustomArithmeticException("Matrix size must be less than 15!");

            double[][] matrix = rows.toArray(new double[0][]);
            boolean[] logicVector = computeLogicVector(matrix);

            showMatrix(matrix, logicVector);
            statusLabel.setText("Matrix loaded successfully");

        } catch (FileNotFoundException e) {
            statusLabel.setText("Error: file not found");
        } catch (NumberFormatException e) {
            statusLabel.setText("Error: invalid number format");
        } catch (CustomArithmeticException e) {
            statusLabel.setText("Error: " + e.getMessage());
        } catch (Exception e) {
            statusLabel.setText("Unknown error: " + e.getMessage());
        }
    }

    // Creating an empty matrix
    private void createEmptyMatrix() {
        try {
            int n = Integer.parseInt(sizeField.getText().trim());
            if (n <= 0 || n > 15)
                throw new CustomArithmeticException("Size must be between 1 and 15");

            String[] columnNames = new String[n];
            for (int i = 0; i < n; i++)
                columnNames[i] = "X" + (i + 1);

            DefaultTableModel model = new DefaultTableModel(columnNames, n);
            matrixTable.setModel(model);
            resultTable.setModel(new DefaultTableModel(new String[] { "L(i)" }, 0));

            statusLabel.setText("Matrix created successfully");
        } catch (NumberFormatException e) {
            statusLabel.setText("Error: invalid number format");
        } catch (CustomArithmeticException e) {
            statusLabel.setText("Error: " + e.getMessage());
        }
    }

    // Calculation from the current table
    private void computeFromTable() {
        try {
            int n = matrixTable.getRowCount();
            if (n == 0)
                throw new CustomArithmeticException("Matrix is empty!");

            double[][] matrix = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Object val = matrixTable.getValueAt(i, j);
                    if (val == null || val.toString().trim().isEmpty())
                        matrix[i][j] = 0;
                    else
                        matrix[i][j] = Double.parseDouble(val.toString().trim());
                }
            }

            boolean[] logicVector = computeLogicVector(matrix);
            showMatrix(matrix, logicVector);
            statusLabel.setText("Done");

        } catch (NumberFormatException e) {
            statusLabel.setText("Error: invalid number format");
        } catch (CustomArithmeticException e) {
            statusLabel.setText("Error: " + e.getMessage());
        } catch (Exception e) {
            statusLabel.setText("Unknown error: " + e.getMessage());
        }
    }

    // Clearing tables
    private void clearTables() {
        matrixTable.setModel(new DefaultTableModel());
        resultTable.setModel(new DefaultTableModel());
        statusLabel.setText("Tables cleared");
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

            if (pos == 0 && neg == 0)
                throw new CustomArithmeticException("Line " + (i + 1) + " all elements = 0");

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

        DefaultTableModel resultModel = new DefaultTableModel(new String[] { "L(i)" }, 0);
        for (boolean v : logicVector)
            resultModel.addRow(new Object[] { v });
        resultTable.setModel(resultModel);
    }
}
