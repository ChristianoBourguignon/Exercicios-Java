package br.com.sudoku.controllers;

import br.com.sudoku.model.GameDifficultyEnum;
import br.com.sudoku.model.GameStatusEnum;
import br.com.sudoku.model.SudokuCell;
import br.com.sudoku.util.LimitNumberInput;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.util.*;
import java.util.List;

import static br.com.sudoku.Main.BOARD_LIMIT;
import static br.com.sudoku.Main.bindFieldToCell;
import static br.com.sudoku.model.GameDifficultyEnum.*;
import static br.com.sudoku.model.GameStatusEnum.*;
import static java.util.Objects.isNull;

public class GameController {
    private Map<JTextField, SudokuCell> gameBoard;
    private GameDifficultyEnum gameDifficulty;

    public GameController(Integer difficulty) {
        switch(difficulty) {
            case 2:
                this.gameDifficulty = MEDIUM;
                break;
            case 3:
                this.gameDifficulty = HARD;
                break;
            default:
                this.gameDifficulty = EASY;
                break;
        }
    }
    public Map<JTextField, SudokuCell> getMap() {
        return gameBoard;
    }

    public GameStatusEnum startedGame() {
        if (!isNull(gameBoard)) {
            return STARTED;
        }
        Random rand = new Random();

        Map<JTextField, SudokuCell> cells = new HashMap<>();
        this.gameBoard = cells;

        // Cria todos os campos inicialmente vazios
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField tf = new JTextField(BOARD_LIMIT);
                SudokuCell cell = new SudokuCell(row, col, 0, false);

                tf.setHorizontalAlignment(JTextField.CENTER);
                tf.setFont(new Font("Arial", Font.BOLD, 18));

                int top = (row % 3 == 0) ? 3 : 1;
                int left = (col % 3 == 0) ? 3 : 1;
                int bottom = (row == 8) ? 3 : 1;
                int right = (col == 8) ? 3 : 1;
                tf.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
                ((AbstractDocument) tf.getDocument()).setDocumentFilter(new LimitNumberInput());

                bindFieldToCell(tf, cell);
                cells.put(tf, cell);
            }
        }

        // Preenche células fixas por quadrante
        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 3; blockCol++) {
                int fixedCells = getFixedCellsPerQuadrant(this.gameDifficulty);

                for (int k = 0; k < fixedCells; k++) {
                    boolean placed = false;
                    while (!placed) {
                        int row = blockRow * 3 + rand.nextInt(3);
                        int col = blockCol * 3 + rand.nextInt(3);

                        SudokuCell cell = getCellAt(cells, row, col);
                        if (cell.isFixed()) continue; // já preenchida

                        int value = rand.nextInt(9) + 1;
                        cell.setValue(value);
                        cell.setFixed(true);

                        if (!hasDuplicateInRowOrColumn(cell)) {
                            // fixa no JTextField
                            JTextField tf = getTextFieldAt(cells, row, col);
                            tf.setText(String.valueOf(value));
                            tf.setEditable(false);
                            tf.setBackground(new Color(220, 220, 220));
                            placed = true;
                        } else {
                            cell.setValue(0);
                            cell.setFixed(false);
                        }
                        JTextField tf = getTextFieldAt(cells, row, col);
                            tf.setText(String.valueOf(value));
                            tf.setEditable(false);
                            tf.setBackground(new Color(220, 220, 220));
                            placed = true;
                    }
                }
            }
        }
        this.gameBoard = cells;
        this.gameBoard.entrySet().stream().forEach(entry -> {
            if (entry.getValue().isFixed()) {
                while(hasDuplicateInRowOrColumn(entry.getValue())) {
                    setValueCells(entry.getValue());
                }
            }
        });
        return STARTED;
    }
    private void setValueCells(SudokuCell cell) {
        Random rand = new Random();
        int value = rand.nextInt(9) + 1;
        cell.setValue(value);
    }

    public void clearGame() {
        if(gameIsStarted()){
            return;
        }
        gameBoard.entrySet().stream()
                .filter(entry -> !entry.getValue().isFixed())
                .forEach(entry -> {
                    entry.getValue().setValue(null);
                    entry.getKey().setText("");
                });
    }

    public GameStatusEnum finishGame() {
        // Verificar se o jogo ta iniciado
        if (gameIsStarted()) {
            return GameStatusEnum.STARTED;
        }

        // Verificar se todas as células estão preenchidas
        if(isHaveCellEmpty()){
            return INCOMPLETE;
        }
        return isCellValueDuplicated();
    }

    private GameStatusEnum isCellValueDuplicated() {
        List<SudokuCell> cellsPositions = gameBoard.values().stream()
                .filter(cell -> !cell.isFixed())
                .toList();
        Optional<SudokuCell> firstDuplicate = cellsPositions.stream()
                .filter(this::hasDuplicateInRowOrColumn)
                .findFirst();

        if (firstDuplicate.isPresent()) {
            return HAS_ERROR;
        } else {
            return COMPLETE;
        }
    }
    public boolean hasDuplicateInRowOrColumn(SudokuCell target) {
        int row = target.getRow();
        int col = target.getCol();
        Integer value = target.getValue();
        if (value == null) return false;

        // verifica na linha
        boolean rowConflict = gameBoard.values().stream()
                .filter(cell -> cell.getRow() == row)
                .anyMatch(cell -> cell != target && value.equals(cell.getValue()));

        // verifica na coluna
        boolean colConflict = gameBoard.values().stream()
                .filter(cell -> cell.getCol() == col)
                .anyMatch(cell -> cell != target && value.equals(cell.getValue()));

        return rowConflict || colConflict;
    }

    private boolean isHaveCellEmpty() {
        Optional<SudokuCell> isHaveCellEmpty = gameBoard.values().stream()
                .filter(cell -> !cell.isFixed())
                .filter(cell -> {
                    Integer value = cell.getValue();
                    return value == null || value == 0;
                })
                .findFirst();
        return isHaveCellEmpty.isPresent();
    }

    public boolean gameIsStarted(){
        return isNull(this.gameBoard);
    }
    private int getFixedCellsPerQuadrant(GameDifficultyEnum difficulty) {
        switch (difficulty) {
            case EASY: return randBetween(3, 4);
            case MEDIUM: return randBetween(2, 3);
            case HARD: return randBetween(1, 2);
            default: return 2;
        }
    }
    private int randBetween(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
    private SudokuCell getCellAt(Map<JTextField, SudokuCell> cells, int row, int col) {
        return cells.values().stream()
                .filter(c -> c.getRow() == row && c.getCol() == col)
                .findFirst().orElse(null);
    }

    private JTextField getTextFieldAt(Map<JTextField, SudokuCell> cells, int row, int col) {
        return cells.entrySet().stream()
                .filter(e -> e.getValue().getRow() == row && e.getValue().getCol() == col)
                .map(Map.Entry::getKey)
                .findFirst().orElse(null);
    }
}
