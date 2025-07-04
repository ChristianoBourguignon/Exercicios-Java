package br.com.contaBancaria.Models;

import java.math.BigDecimal;

public class ContaBancaria {
    private final String nome;
    private BigDecimal saldo;
    private BigDecimal chequeEspecial;
    private boolean usandoChequeEspecial;
    private BigDecimal limitCheque;

    public ContaBancaria(String nome,BigDecimal saldo, BigDecimal chequeEspecial) {
        this.nome = nome;
        this.saldo = saldo;
        this.chequeEspecial = chequeEspecial;
        this.usandoChequeEspecial = false;
        this.limitCheque = chequeEspecial;
    }
    public String getNome() {
        return nome;
    }

    public boolean getUsandoChequeEspecial() {
        return this.usandoChequeEspecial;
    }
    public void setUsandoChequeEspecial(boolean usandoChequeEspecial) {
        this.usandoChequeEspecial = usandoChequeEspecial;
    }
    public BigDecimal getSaldo() {
        return this.saldo;
    }
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
    public BigDecimal getChequeEspecial() {
        return chequeEspecial;
    }
    public void setChequeEspecial(BigDecimal ChequeEspecial) {
        this.chequeEspecial = ChequeEspecial;
    }
    public BigDecimal getSaldoTotal() {
        return this.getSaldo().add(this.chequeEspecial);
    }
    public BigDecimal getLimitCheque() {
        return this.limitCheque;
    }
    public void setLimitCheque(BigDecimal limitCheque) {
        this.limitCheque = limitCheque;
    }
}
