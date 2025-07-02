package br.com.contaBancaria.Views;

import br.com.contaBancaria.Models.ContaBancaria;

import java.text.NumberFormat;

public record ContaBancariaViews(ContaBancaria conta) {
    public void exibirSaldo(){
        System.out.println("======================");
        System.out.println("Saldo da sua conta: " + NumberFormat.getCurrencyInstance().format(this.conta.getSaldo()));
        System.out.println("======================");

    }
    public void ExibirChequeEspecial(){
        System.out.println("======================");
        System.out.println("Cheque especial: " + NumberFormat.getCurrencyInstance().format(this.conta.getChequeEspecial()));
        System.out.println("======================");

    }
}
