package br.com.contaBancaria.Models;

public class ContaBancaria {
    private String nome;
    private Double saldo;
    private Double ChequeEspecial;
    public ContaBancaria(String nome,Double saldo, Double ChequeEspecial) {
        this.nome = nome;
        this.saldo = saldo;
        this.ChequeEspecial = ChequeEspecial;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSaldo() {
        return this.saldo+this.ChequeEspecial;
    }
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    public Double getChequeEspecial() {
        return ChequeEspecial;
    }
    public void setChequeEspecial(Double ChequeEspecial) {
        this.ChequeEspecial = ChequeEspecial;
    }
}
