package models;

import java.util.Objects;

public class Calculo {
    private float numero1;
    private float numero2;
    private String operacao;

    public Calculo(float numero1, float numero2, String operacao) {
        this.numero1 = numero1;
        this.numero2 = numero2;
        this.operacao = operacao;
    }
    public float getResultado() {
        switch (operacao){
            case "+":
                return numero1 + numero2;
            case "-":
                return numero1 - numero2;
            case "/":
                return numero1 / numero2;
            case "*":
                return numero1 * numero2;
            default:
                return 0;
        }
    }
}
