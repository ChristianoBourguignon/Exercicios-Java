package br.com.sudoku.model;

import java.util.Collection;
import java.util.List;

import static br.com.sudoku.model.GameStatusEnum.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Board {
    private final List<List<Space>> spaces;

    public Board(List<List<Space>> spaces) {
        this.spaces = spaces;
    }
    public List<List<Space>> getSpaces() {
        return spaces;
    }
    public GameStatusEnum getGameStatus() {
        if(spaces.stream().flatMap(Collection::stream)
                .noneMatch(s->!s.isFixed() && nonNull(s.getActual()))){
            return NON_STARTED;
        }
        return spaces.stream()
                .flatMap(Collection::stream)
                .anyMatch(s->isNull(s.getActual())) ? INCOMPLETE : COMPLETE;
    }
    public boolean hasErrors(){
        if(getGameStatus() == NON_STARTED){return false;}
        return spaces.stream()
                .flatMap(Collection::stream)
                .anyMatch(s->!s.getActual().equals(s.getExpected()));
    }
    public boolean changeValue(final int column, final int row, final int value){
        Space space = spaces.get(column).get(row);
        if(space.isFixed()){return false;}
        space.setActual(value);
        return true;
    }
    public boolean clearValue(final int column, final int row){
        Space space = spaces.get(column).get(row);
        if(space.isFixed()){return false;}
        space.clearSpace();
        return true;
    }
    public void reset(){
        spaces.stream()
                .flatMap(Collection::stream)
                .filter(s -> !s.isFixed())
                .forEach(Space::clearSpace);
    }
    public boolean gameIsFinished(){
        return !hasErrors() && getGameStatus().equals(COMPLETE);
    }
}
