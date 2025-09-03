package br.com.sudoku.services;

import br.com.sudoku.model.Board;
import br.com.sudoku.model.GameStatusEnum;
import br.com.sudoku.model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static br.com.sudoku.Main.BOARD_LIMIT;

public class BoardService {
    private final Board board;

    public BoardService(final Map<String,String> gameConfig) {
        this.board = new Board(initBoard(gameConfig));
    }

    public List<List<Space>> getSpace(){
        return this.board.getSpaces();
    }
    public void reset(){
        this.board.reset();
    }
    public boolean hasErrors(){
        return this.board.hasErrors();
    }
    public GameStatusEnum getGameStatus(){
        return this.board.getGameStatus();
    }
    public boolean gameIsFinished(){
        return this.board.gameIsFinished();
    }

    private List<List<Space>> initBoard(final Map<String, String> gameConfig) {
        List<List<Space>> spaces = new ArrayList<>();
        for(int i=0; i < BOARD_LIMIT; i++){
            spaces.add(new ArrayList<>());
            for(int j=0; j < BOARD_LIMIT; j++){
                String positionConfig = gameConfig.get("%s,%s".formatted(i,j));
                int position = Integer.parseInt(positionConfig.split(",")[0]);
                boolean fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                Space currentSpace = new Space(position,fixed);
                spaces.get(i).add(currentSpace);
            }
        }
        return spaces;
    }
}
