package br.com.sudoku.model;

public enum GameDifficultyEnum {
    EASY("Fácil"),
    MEDIUM("Intermediário"),
    HARD("Avançado");

    private String label;
    GameDifficultyEnum(final String label){
        this.label = label;
    }
    public String getLabel(){
        return label;
    }
}
