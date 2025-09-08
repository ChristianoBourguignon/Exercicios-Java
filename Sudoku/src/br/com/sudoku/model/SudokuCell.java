package br.com.sudoku.model;

public class SudokuCell {
    private final int row;
    private final int col;
    private Integer value;
    private boolean isFixed;

    public SudokuCell(int row, int col,int value, boolean isFixed) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.isFixed = isFixed;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public Integer getValue() {
        return value;
    }
    public boolean isFixed() {
        return isFixed;
    }
    public void setFixed(boolean isFixed) {
        this.isFixed = isFixed;
    }
    public void setValue(Integer value){
        this.value = value;
    }
}
