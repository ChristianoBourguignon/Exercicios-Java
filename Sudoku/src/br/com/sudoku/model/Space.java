package br.com.sudoku.model;

public class Space {
    private Integer actual;
    private final int expected;
    private final boolean fixed;

    public Space(int expected, boolean fixed) {
        this.expected = expected;
        this.fixed = fixed;
        if(fixed) {
            this.actual = expected;
        }
    }
    public final Integer getActual() {
        return actual;
    }

    public void setActual(Integer actual) {
        if(this.fixed) return;
        this.actual = actual;
    }
    public void clearSpace(){
        setActual(null);
    }

    public final int getExpected() {
        return expected;
    }
    public boolean isFixed() {
        return fixed;
    }
}
