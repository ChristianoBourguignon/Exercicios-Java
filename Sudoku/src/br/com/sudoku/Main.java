package br.com.sudoku;

import br.com.sudoku.model.Board;
import br.com.sudoku.model.Space;
import br.com.sudoku.ui.View;
import br.com.sudoku.util.SudokuCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static br.com.sudoku.util.BoardTemplate.BOARD_TEMPLATE;
import static java.sql.Types.NULL;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Main {
    private final static Scanner sc = new Scanner(System.in);
    private static Board board;
    public final static int BOARD_LIMIT = 9;

    public static void main(String[] args) {
        final List<SudokuCell> cells = Stream.of(args)
                .map(s -> {
                    String[] parts = s.split(";");
                    String[] positions = parts[0].split(",");
                    String[] data = parts[1].split(",");
                    int row = Integer.parseInt(positions[0]);
                    int col = Integer.parseInt(positions[1]);
                    int value = 0;
                    boolean isFixed = Boolean.parseBoolean(data[1]);
                    if (isFixed) {
                        value = Integer.parseInt(data[0]);
                    }
                    return new SudokuCell(row, col, value, isFixed);
                })
                .toList();
        new View(cells);
    }
//        int option = -1;
//        while (true){
//            System.out.println("Selecione uma das opções a seguir");
//            System.out.println("1 - Iniciar um novo Jogo");
//            System.out.println("2 - Colocar um novo número");
//            System.out.println("3 - Remover um número");
//            System.out.println("4 - Visualizar jogo atual");
//            System.out.println("5 - Verificar status do jogo");
//            System.out.println("6 - limpar jogo");
//            System.out.println("7 - Finalizar jogo");
//            System.out.println("8 - Sair");
//
//            option = sc.nextInt();
//
//            switch (option){
//                case 1 -> startGame(positions);
//                case 2 -> inputNumber();
//                case 3 -> removeNumber();
//                case 4 -> showCurrentGame();
//                case 5 -> showGameStatus();
//                case 6 -> clearGame();
//                case 7 -> finishGame();
//                case 8 -> System.exit(0);
//                default -> System.out.println("Opção inválida, selecione uma das opções do menu");
//            }
//        }
//    }
//
//    private static void startGame(Map<String, String> positions) {
//        List<List<Space>> spaces = new ArrayList<>();
//        for(int i=0; i < BOARD_LIMIT; i++){
//            spaces.add(new ArrayList<>());
//            for(int j=0; j < BOARD_LIMIT; j++){
//                String positionConfig = positions.get("%s,%s".formatted(i,j));
//                int position = Integer.parseInt(positionConfig.split(",")[0]);
//                boolean fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
//                Space currentSpace = new Space(position,fixed);
//                spaces.get(i).add(currentSpace);
//            }
//        }
//        board = new Board(spaces);
//        System.out.println("O jogo está para começar");
//    }
//
//    private static void inputNumber() {
//        if(gameIsStarted()){
//            return;
//        }
//        System.out.println("Digite um numero da coluna a ser inserido: ");
//        int col = runUntilGetValidNumber(0,8);
//        System.out.println("Digite um numero da linha a ser inserido: ");
//        int row = runUntilGetValidNumber(0,8);
//
//        System.out.printf("Informe o numero que vai entrar na posição [%s,%s]\n",col, row);
//        int value = runUntilGetValidNumber(1,9);
//        if(!board.changeValue(col,row,value)){
//            System.out.printf("A posição [%s,%s] tem um valor fixo\n",col,row);
//        }
//    }
//
//    private static void removeNumber() {
//        if(gameIsStarted()){
//            return;
//        }
//        System.out.println("Digite um numero da coluna a ser inserido: ");
//        int col = runUntilGetValidNumber(0,8);
//        System.out.println("Digite um numero da linha a ser inserido: ");
//        int row = runUntilGetValidNumber(0,8);
//
//        if(!board.clearValue(col,row)){
//            System.out.printf("A posição [%s,%s] tem um valor fixo\n",col,row);
//        }
//    }
//
//    private static void showCurrentGame() {
//        if(gameIsStarted()){
//            return;
//        }
//        var args = new Object[81];
//        var argPos = 0;
//        for (int i = 0; i < BOARD_LIMIT; i++) {
//            for (var col: board.getSpaces()){
//                args[argPos ++] = " " + ((isNull(col.get(i).getActual())) ? " " : col.get(i).getActual());
//            }
//        }
//        System.out.println("O jogo se encontra da seguinte forma");
//        System.out.printf((BOARD_TEMPLATE) + "\n",args);
//
//    }
//
//    private static void showGameStatus() {
//        if(gameIsStarted()){
//            return;
//        }
//        System.out.printf("O jogo se encontra no status %s\n", board.getGameStatus());
//    }
//
//    private static void clearGame() {
//        if(gameIsStarted()){
//            return;
//        }
//        System.out.println("Tem certeza que deseja limpar seu jogo e perder todo seu progresso? Ex: sim ou não");
//        var confirm = sc.next();
//        while(!confirm.equalsIgnoreCase("sim") && !confirm.equalsIgnoreCase("nao")){
//            System.out.println("Informe apenas o: sim ou não ");
//            confirm = sc.next();
//        }
//        if(confirm.equalsIgnoreCase("sim")){
//            board.reset();
//        }
//    }
//
//    private static void finishGame() {
//        if(gameIsStarted()){
//            return;
//        }
//        if(board.gameIsFinished()){
//            System.out.println("Parabéns você concluiu o jogo");
//            showCurrentGame();
//            board = null;
//        } else if (board.hasErrors()) {
//            System.out.println("Seu jogo contém erros, verifique seu board e ajuste-o");
//        } else {
//            System.out.println("Seu jogo tem dados ausentes, verifique seu board e ajuste-o");
//        }
//    }
//    private static int runUntilGetValidNumber(final int min, final int max) {
//        int current = sc.nextInt();
//        while(current < min || current > max){
//            System.out.printf("Informe um numero entre: %s e %s\n",min,max);
//            current = sc.nextInt();
//        }
//        return current;
//    }
//
//    private static boolean gameIsStarted(){
//        if(isNull(board)){
//            System.out.println("O jogo não foi iniciado");
//            return true;
//        } else {
//            return false;
//        }
//    }
}