package br.com.contaBancaria.Models;

import br.com.contaBancaria.Exception.DadosInvalidosException;
import br.com.contaBancaria.Exception.ExceptionCustom;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContaBancaria {
    private final String nome;
    private BigDecimal saldo;
    private BigDecimal chequeEspecial;
    private boolean usandoChequeEspecial;
    private BigDecimal limitCheque;

    public ContaBancaria(String nome,BigDecimal saldo) {

        if (saldo == null || saldo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ExceptionCustom("Erro ao criar a conta bancária", new DadosInvalidosException("Valor para adicionar deve ser maior que zero."));
        }
        this.nome = nome;
        this.saldo = saldo;
        this.usandoChequeEspecial = false;

        if (saldo.compareTo(new BigDecimal("500.00")) <= 0) {
            this.limitCheque = new BigDecimal("50.00");
            this.chequeEspecial = new BigDecimal("50.00");
        } else {
            BigDecimal divisor = new BigDecimal("2");
            BigDecimal limite = saldo.divide(divisor,RoundingMode.DOWN);
            this.limitCheque = limite;
            this.chequeEspecial = limite;
        }

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
