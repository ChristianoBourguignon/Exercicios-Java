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
import static br.com.sudoku.model.GameDifficultyEnum.*;
import static br.com.sudoku.model.GameStatusEnum.*;
import static java.util.Objects.isNull;

public class GameController {
    private Map<JTextField, SudokuCell> gameBoard;
    private GameDifficultyEnum gameDifficulty;
    private GameStatusEnum gameStatus;

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
        this.gameStatus = NON_STARTED;
    }
    public Map<JTextField, SudokuCell> getMap() {
        return gameBoard;
    }
    public boolean gameIsStarted(){
        return isNull(gameBoard);
    }

    public void setGameStatus(GameStatusEnum gameDifficulty) {this.gameStatus = gameDifficulty;}

    public GameStatusEnum checkStatus(){
        return gameStatus;
    }

    public GameStatusEnum startedGame() {

        if(this.gameStatus.equals(STARTED)) {
            return IN_STARTED;
        }

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

        // Gera um Sudoku válido completo primeiro
        generateValidSudoku(cells);
        
        // Remove células baseado na dificuldade para criar o puzzle
        removeCellsForDifficulty(cells);
        
        this.gameBoard = cells;
        this.gameStatus = IN_STARTED;
        return STARTED;
    }

    /**
     * Retorna true ou false, se haver o mesmo numero na linha,coluna ou quadrante..
     */
    public boolean hasDuplicateInRowOrColumnOrBlock(SudokuCell target) {
        if (target == null) return false;

        // verifica na linha
        boolean rowConflict = hasDuplicateInRow(target);

        // verifica na coluna
        boolean colConflict = hasDuplicateInCol(target);

        // verifica o quadrante
        boolean blockConflict = hasDuplicateInBlock(target);

        return rowConflict || colConflict || blockConflict;
    }
    /**
     * Retorna true ou false, se haver o mesmo numero no quadrante 3x3.
     */
    public boolean hasDuplicateInBlock(SudokuCell target) {
        int row = target.getRow();
        int col = target.getCol();
        Integer value = target.getValue();
        if (value == null || value == 0) return false;

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        return gameBoard.values().stream()
                .filter(cell -> cell != target)
                .filter(cell -> cell.getRow() >= startRow && cell.getRow() < startRow + 3)
                .filter(cell -> cell.getCol() >= startCol && cell.getCol() < startCol + 3)
                .anyMatch(cell -> value.equals(cell.getValue()));
    }
    /**
     * Retorna true ou false, se haver o mesmo numero na linha.
     */
    public boolean hasDuplicateInRow(SudokuCell target) {
        int row = target.getRow();
        Integer value = target.getValue();
        if (value == null || value == 0) return false;

        return gameBoard.values().stream()
                .filter(cell -> cell.getRow() == row)
                .anyMatch(cell -> cell != target && value.equals(cell.getValue()));
    }
    /**
     * Retorna true ou false, se haver o mesmo numero na coluna.
     */
    public boolean hasDuplicateInCol(SudokuCell target) {
        int col = target.getCol();
        Integer value = target.getValue();
        if (value == null || value == 0) return false;

        return gameBoard.values().stream()
                .filter(cell -> cell.getCol() == col)
                .anyMatch(cell -> cell != target && value.equals(cell.getValue()));
    }

    /**
     * Limpa todos os valores dos JTextField, cujo as SudokuCell nao sejam fixas.
     */
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

    /**
     * Retorna status do jogo
     */

    public GameStatusEnum finishGame() {
        // Verificar se o jogo ta iniciado
        if (gameIsStarted()) {
            return NON_STARTED;
        }

        // Verificar se todas as células estão preenchidas
        if(isHaveCellEmpty()){
            return INCOMPLETE;
        }
        return isCellValueDuplicated();
    }

    /**
     * Retorna o status do jogo, caso tenha valores duplicadas no JTextField
     */

    private GameStatusEnum isCellValueDuplicated() {
        List<SudokuCell> cellsPositions = gameBoard.values().stream()
                .filter(cell -> !cell.isFixed())
                .toList();
        Optional<SudokuCell> validateInputs = cellsPositions.stream()
                .filter(this::hasDuplicateInRowOrColumnOrBlock)
                .findFirst();

        if (validateInputs.isPresent()) {
            return HAS_ERROR;
        } else {
            return COMPLETE;
        }
    }
    /**
     * Retorna true ou false, caso tenha celula vazia.
     */
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

    /**
     * Retorna a SudokuCell que esta na posicao especificada no row  e col e se caso nao tiver, retorna null
     */
    private SudokuCell getCellAt(Map<JTextField, SudokuCell> cells, int row, int col) {
        return cells.values().stream()
                .filter(c -> c.getRow() == row && c.getCol() == col)
                .findFirst().orElse(null);
    }

    /**
     * Retorna o JTextField que esta na posicao especificada no row  e col e se caso nao tiver, retorna null
     */
    private JTextField getTextFieldAt(Map<JTextField, SudokuCell> cells, int row, int col) {
        return cells.entrySet().stream()
                .filter(e -> e.getValue().getRow() == row && e.getValue().getCol() == col)
                .map(Map.Entry::getKey)
                .findFirst().orElse(null);
    }

    /**
     * Gera um Sudoku válido completo usando backtracking
     */
    private void generateValidSudoku(Map<JTextField, SudokuCell> cells) {
        Random rand = new Random();
        
        // Preenche a diagonal principal dos blocos 3x3 primeiro (mais fácil de validar)
        for (int block = 0; block < 3; block++) {
            fillDiagonalBlock(cells, block, rand);
        }
        
        // Preenche o resto do tabuleiro usando backtracking
        solveSudoku(cells, 0, 0);
    }

    /**
     * Preenche um bloco diagonal 3x3 com números válidos
     */
    private void fillDiagonalBlock(Map<JTextField, SudokuCell> cells, int block, Random rand) {
        int startRow = block * 3;
        int startCol = block * 3;
        
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers, rand);
        
        int index = 0;
        for (int row = startRow; row < startRow + 3; row++) {
            for (int col = startCol; col < startCol + 3; col++) {
                SudokuCell cell = getCellAt(cells, row, col);
                cell.setValue(numbers.get(index++));
            }
        }
    }

    /**
     * Resolve o Sudoku usando backtracking
     */
    private boolean solveSudoku(Map<JTextField, SudokuCell> cells, int row, int col) {
        if (row == 9) {
            return true; // Tabuleiro completo
        }
        
        if (col == 9) {
            return solveSudoku(cells, row + 1, 0);
        }
        
        SudokuCell cell = getCellAt(cells, row, col);
        if (cell.getValue() != null && cell.getValue() != 0) {
            return solveSudoku(cells, row, col + 1);
        }
        
        // Tenta números de 1 a 9
        for (int num = 1; num <= 9; num++) {
            cell.setValue(num);
            if (!hasDuplicateInRowOrColumnOrBlock(cell)) {
                if (solveSudoku(cells, row, col + 1)) {
                    return true;
                }
            }
            cell.setValue(0);
        }
        
        return false;
    }

    /**
     * Remove células baseado na dificuldade para criar o puzzle
     */
    private void removeCellsForDifficulty(Map<JTextField, SudokuCell> cells) {
        Random rand = new Random();
        int cellsToRemove = getCellsToRemove(this.gameDifficulty);
        
        List<SudokuCell> allCells = new ArrayList<>(cells.values());
        Collections.shuffle(allCells, rand);
        
        int removed = 0;
        for (SudokuCell cell : allCells) {
            if (removed >= cellsToRemove) break;
            
            // Remove o valor da célula
            cell.setValue(0);
            cell.setFixed(false);
            
            // Limpa o campo de texto
            JTextField tf = getTextFieldAt(cells, cell.getRow(), cell.getCol());
            tf.setText("");
            tf.setEditable(true);
            tf.setBackground(Color.WHITE);
            
            removed++;
        }
        
        // Marca as células restantes como fixas
        for (SudokuCell cell : cells.values()) {
            if (cell.getValue() != null && cell.getValue() != 0) {
                cell.setFixed(true);
                JTextField tf = getTextFieldAt(cells, cell.getRow(), cell.getCol());
                tf.setText(String.valueOf(cell.getValue())); // Adiciona o valor ao campo de texto
                tf.setEditable(false);
                tf.setBackground(new Color(220, 220, 220));
            }
        }
    }

    /**
     * Retorna o número de células a serem removidas baseado na dificuldade
     */
    private int getCellsToRemove(GameDifficultyEnum difficulty) {
        return switch (difficulty) {
            case MEDIUM -> 50;
            case HARD -> 60;
            default -> 30;
        };
    }

    public static void bindFieldToCell(JTextField field, SudokuCell cell) {
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
