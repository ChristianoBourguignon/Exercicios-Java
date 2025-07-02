package br.com.contaBancaria.Models;

import java.math.BigDecimal;

public class ContaBancaria {
    private String nome;
    private BigDecimal saldo;
    private BigDecimal saldoTotal;
    private BigDecimal ChequeEspecial;
    private boolean usandoChequeEspecial;
    public ContaBancaria(String nome,BigDecimal saldo, BigDecimal ChequeEspecial) {
        this.nome = nome;
        this.saldo = saldo;
        this.ChequeEspecial = ChequeEspecial;
        this.usandoChequeEspecial = false;
        this.saldoTotal = getSaldoTotal();
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
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
        return ChequeEspecial;
    }
    public void setChequeEspecial(BigDecimal ChequeEspecial) {
        this.ChequeEspecial = ChequeEspecial;
    }
    public BigDecimal getSaldoTotal() {
        return this.getSaldo().add(this.ChequeEspecial);
    }
    public void setSaldoTotal(BigDecimal saldo) {this.saldoTotal = saldo;}
}
