package br.com.sudoku.controllers;

import br.com.sudoku.model.GameStatusEnum;
import br.com.sudoku.util.SudokuCell;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static br.com.sudoku.model.GameStatusEnum.*;
import static java.util.Objects.isNull;

public class GameController {
    final private List<SudokuCell> gameBoard;

    public GameController(List<SudokuCell> cells) {
        this.gameBoard = cells;
    }

    private void inputNumber() {
        if(gameIsStarted()){
            return;
        }
        System.out.println("Digite um numero da coluna a ser inserido: ");
        int col = runUntilGetValidNumber(0,8);
        System.out.println("Digite um numero da linha a ser inserido: ");
        int row = runUntilGetValidNumber(0,8);

        System.out.printf("Informe o numero que vai entrar na posição [%s,%s]\n",col, row);
        int value = runUntilGetValidNumber(1,9);
//        if(!gameBoard.changeValue(col,row,value)){
//            System.out.printf("A posição [%s,%s] tem um valor fixo\n",col,row);
//        }
    }

    private void removeNumber() {
        if(gameIsStarted()){
            return;
        }
        System.out.println("Digite um numero da coluna a ser inserido: ");
        int col = runUntilGetValidNumber(0,8);
        System.out.println("Digite um numero da linha a ser inserido: ");
        int row = runUntilGetValidNumber(0,8);

//        if(!gameBoard.clearValue(col,row)){
//            System.out.printf("A posição [%s,%s] tem um valor fixo\n",col,row);
//        }
    }

    private void showCurrentGame() {
        if(gameIsStarted()){
            return;
        }
        var args = new Object[81];
        var argPos = 0;
//        for (int i = 0; i < BOARD_LIMIT; i++) {
//            for (var col: board.getSpaces()){
//                args[argPos ++] = " " + ((isNull(col.get(i).getActual())) ? " " : col.get(i).getActual());
//            }
//        }
//        System.out.println("O jogo se encontra da seguinte forma");
//        System.out.printf((BOARD_TEMPLATE) + "\n",args);

    }

    private void showGameStatus() {
        if(gameIsStarted()){
            return;
        }
//        System.out.printf("O jogo se encontra no status %s\n", board.getGameStatus());
    }

    private void clearGame() {
        if(gameIsStarted()){
            return;
        }
        System.out.println("Tem certeza que deseja limpar seu jogo e perder todo seu progresso? Ex: sim ou não");
//        var confirm = sc.next();
//        while(!confirm.equalsIgnoreCase("sim") && !confirm.equalsIgnoreCase("nao")){
//            System.out.println("Informe apenas o: sim ou não ");
//            confirm = sc.next();
//        }
//        if(confirm.equalsIgnoreCase("sim")){
//            board.reset();
//        }
    }

    private GameStatusEnum finishGame(Map<JTextField, SudokuCell> sudokuFields) {
        if (gameIsStarted()) {
            return GameStatusEnum.STARTED;
        }

        // Verificar se todas as células estão preenchidas
        for (SudokuCell cell : sudokuFields.values()) {
            if (cell.getValue() == 0) {
                return GameStatusEnum.INCOMPLETE;
            }
        }

        // Validar todas as células
        for (SudokuCell cell : sudokuFields.values()) {
            int row = cell.getRow();
            int col = cell.getCol();
            int value = cell.getValue();

            // Valida linha
            for (SudokuCell other : sudokuFields.values()) {
                if (other != cell && other.getRow() == row && other.getValue() == value) {
                    return HAS_ERROR;
                }
            }

            // Valida coluna
            for (SudokuCell other : sudokuFields.values()) {
                if (other != cell && other.getCol() == col && other.getValue() == value) {
                    return HAS_ERROR;
                }
            }

            // Valida quadrante 3x3
            int startRow = (row / 3) * 3;
            int startCol = (col / 3) * 3;
            for (SudokuCell other : sudokuFields.values()) {
                if (other != cell) {
                    int otherRow = other.getRow();
                    int otherCol = other.getCol();
                    if (otherRow >= startRow && otherRow < startRow + 3 &&
                            otherCol >= startCol && otherCol < startCol + 3 &&
                            other.getValue() == value) {
                        return HAS_ERROR;
                    }
                }
            }
        }

        // Se passou em todas as validações
        return COMPLETE;
    }

    private int runUntilGetValidNumber(final int min, final int max) {
//        int current = sc.nextInt();
//        while(current < min || current > max){
//            System.out.printf("Informe um numero entre: %s e %s\n",min,max);
//            current = sc.nextInt();
//        }
        return 0;
    }

    private boolean gameIsStarted(){
        if(isNull(this.gameBoard)){
            System.out.println("O jogo não foi iniciado");
            return true;
        } else {
            return false;
        }
    }
}
