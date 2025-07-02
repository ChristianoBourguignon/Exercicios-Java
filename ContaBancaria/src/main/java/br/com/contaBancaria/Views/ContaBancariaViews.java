package br.com.contaBancaria.Views;

import br.com.contaBancaria.Models.ContaBancaria;

public record ContaBancariaViews(ContaBancaria conta) {
    public void exibirSaldo(){
        System.out.println("======================");
        System.out.println("Saldo da sua conta: " + this.conta.getSaldo());
        System.out.println("======================");

    }
    public void ExibirChequeEspecial(){
        System.out.println("======================");
        System.out.println("Cheque especial: " + this.conta.getChequeEspecial());
        System.out.println("======================");

    }
    public double getSaldo(){
        return this.conta.getSaldo();
    }
    public double getChequeEspecial(){
        return this.conta.getChequeEspecial();
    }
}
