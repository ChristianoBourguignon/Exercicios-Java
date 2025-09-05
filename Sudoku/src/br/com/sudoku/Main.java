package br.com.sudoku;

import br.com.sudoku.ui.View;
import br.com.sudoku.model.SudokuCell;
import br.com.sudoku.util.LimitNumberInput;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public final static int BOARD_LIMIT = 9;

    public static void main(String[] args) {
        Map<JTextField, SudokuCell> cells = Stream.of(args)
                .map(s -> {
                    JTextField tf = new JTextField(BOARD_LIMIT);

                    String[] parts = s.split(";");
                    String[] positions = parts[0].split(",");
                    String[] data = parts[1].split(",");

                    int row = Integer.parseInt(positions[0]);
                    int col = Integer.parseInt(positions[1]);
                    boolean isFixed = Boolean.parseBoolean(data[1]);

                    int value = isFixed ? Integer.parseInt(data[0]) : 0;

                    SudokuCell cell = new SudokuCell(row, col, value, isFixed);

                    if (isFixed) {
                        tf.setText(String.valueOf(value));
                        tf.setEditable(false);
                        tf.setBackground(new Color(220, 220, 220));
                    }
                    tf.setHorizontalAlignment(JTextField.CENTER);
                    tf.setFont(new Font("Arial", Font.BOLD, 18));
                    int top = (row % 3 == 0) ? 3 : 1;
                    int left = (col % 3 == 0) ? 3 : 1;
                    int bottom = (row == 8) ? 3 : 1;
                    int right = (col == 8) ? 3 : 1;
                    tf.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
                    ((AbstractDocument) tf.getDocument()).setDocumentFilter(new LimitNumberInput());

                    // **Liga o JTextField ao SudokuCell**
                    bindFieldToCell(tf, cell);

                    return new AbstractMap.SimpleEntry<>(tf, cell);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        new View(cells);
    }

    private static void bindFieldToCell(JTextField field, SudokuCell cell) {
        field.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateCell();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateCell();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateCell();
            }

            private void updateCell() {
                String text = field.getText();
                if (text.isEmpty()) {
                    cell.setValue(null);
                } else {
                    try {
                        cell.setValue(Integer.parseInt(text));
                    } catch (NumberFormatException ex) {
                        cell.setValue(null);
                    }
                }
            }
        });
    }

}