package br.com.sudoku.controllers;

import br.com.sudoku.model.GameStatusEnum;
import br.com.sudoku.util.SudokuCell;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.sudoku.model.GameStatusEnum.*;
import static java.util.Objects.isNull;

public class GameController {
    private Map<JTextField, SudokuCell> gameBoard;

    public GameController(Map<JTextField, SudokuCell> gameBoard) {
        this.gameBoard = gameBoard;
    }
    public Map<JTextField, SudokuCell> getMap() {
        return gameBoard;
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

    private boolean gameIsStarted(){
        return isNull(this.gameBoard);
    }
}
