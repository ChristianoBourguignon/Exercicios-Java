package br.com.sudoku.util;

public class SudokuCell {
    private int x;
    private int y;
    private Integer value;
    private boolean fixed;

    public SudokuCell(int x, int y, int value, boolean fixed) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.fixed = fixed;
    }

    public int getRow() { return x; }
    public int getCol() { return y; }
    public Integer getValue() { return value; }
    public boolean isFixed() { return fixed; }
    public void clearValue(int value) { this.value = value; }
    public void setValue(Integer value) { this.value = value; }
}
