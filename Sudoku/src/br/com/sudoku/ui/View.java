package br.com.sudoku.ui;

import br.com.sudoku.controllers.GameController;
import br.com.sudoku.ui.custom.Button.ExitGameButton;
import br.com.sudoku.ui.custom.Button.ResetGameButton;
import br.com.sudoku.ui.custom.Button.StartGameButton;
import br.com.sudoku.util.SudokuCell;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class View extends JFrame implements ActionListener {

    private StartGameButton btnStart;
    private ExitGameButton btnExit;
    private ResetGameButton btnReset;
    private JTextField[][] cells = new JTextField[9][9];
    private List<SudokuCell> sudokuCells;

    public View(List<SudokuCell> sudokuCells) {
        this.sudokuCells = sudokuCells;

        this.setTitle("Sudoku");
        this.setSize(600, 800);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Painel do tabuleiro 9x9
        JPanel boardPanel = new JPanel(new GridLayout(9, 9));
        boardPanel.setBounds(50, 20, 500, 500);

        // Criar os campos da matriz
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                JTextField field = new JTextField();
                field.setHorizontalAlignment(JTextField.CENTER);
                field.setFont(new Font("Arial", Font.BOLD, 18));
                int top = (y % 3 == 0) ? 3 : 1;
                int left = (x % 3 == 0) ? 3 : 1;
                int bottom = (y == 8) ? 3 : 1;
                int right = (x == 8) ? 3 : 1;
                field.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

                cells[y][x] = field;
                boardPanel.add(field);
            }
        }
        Map<JTextField, SudokuCell> sudokuFields = new HashMap<>();

        // Preencher as células
        for (SudokuCell sc : sudokuCells) {
            JTextField field = cells[sc.getRow()][sc.getCol()];
            field.setText(sc.getValue() == 0 ? "" : String.valueOf(sc.getValue()));

            // Se a célula for fixa
            if (sc.isFixed()) {
                field.setEditable(false);
                field.setBackground(new Color(220, 220, 220));
            } else {
                // Restringir a entrada
                field.setDocument(new PlainDocument() {
                    @Override
                    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                        if (str == null) return;

                        // Permitir apenas dígitos de 1 a 9
                        if (str.matches("[1-9]") && getLength() == 0) {
                            super.insertString(offset, str, attr);
                        }
                    }
                });

                // Listener para atualização do modelo
                field.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) { atualizar(); }
                    @Override
                    public void removeUpdate(DocumentEvent e) { atualizar(); }
                    @Override
                    public void changedUpdate(DocumentEvent e) { atualizar(); }

                    private void atualizar() {
                        String text = field.getText().trim();
                        if (text.isEmpty()) {
                            sc.setValue(0);
                        } else {
                            try {
                                int value = Integer.parseInt(text);
                                if (value >= 1 && value <= 9) {
                                    sc.setValue(value);
                                } else {
                                    sc.setValue(0);
                                }
                            } catch (NumberFormatException ex) {
                                sc.setValue(0);
                            }
                        }
                    }
                });
            }
            sudokuFields.put(field, sc);
        }

        // Botões
        btnStart = new StartGameButton();
        btnExit = new ExitGameButton();
        btnReset = new ResetGameButton();

        btnReset.addActionListener(this);
        btnStart.addActionListener(this);
        btnExit.addActionListener(this);

        this.add(boardPanel);
        this.add(btnStart);
        this.add(btnReset);
        this.add(btnExit);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnStart) {
            JOptionPane.showMessageDialog(this, "Jogo iniciado!");
        }
//        if(e.getSource() == btnFinalizarJogo) {
//            JOptionPane.showMessageDialog(this, "Jogo finalizado!");
//            gc.finishGame(sudokuFields);
//        }
        if(e.getSource() == btnReset) {
            int resposta = JOptionPane.showConfirmDialog(
                    this,
                    "Você realmente quer resetar os valores inseridos?",
                    "Resetar Sudoku",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (resposta == JOptionPane.YES_OPTION) {
                sudokuCells.stream()
                        .filter(sc -> !sc.isFixed())
                        .forEach(sc -> sc.setValue(0));
            }
        }
        if(e.getSource() == btnExit) {
            System.exit(0);
        }
    }
}
