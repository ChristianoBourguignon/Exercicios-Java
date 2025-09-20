package br.com.sudoku.model;

public enum GameStatusEnum {
    NON_STARTED("Não iniciado"),
    STARTED("Iniciado"),
    INCOMPLETE("Incompleto"),
    COMPLETE("Completo"),
    HAS_ERROR("Contém Erros"),
    IN_STARTED("Iniciado");

    private String label;
    GameStatusEnum(final String label){
        this.label = label;
    }
    public String getLabel(){
        return label;
    }
}
