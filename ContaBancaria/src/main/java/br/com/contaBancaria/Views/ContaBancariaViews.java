package br.com.contaBancaria.Views;

import br.com.contaBancaria.Controllers.ContaControllers;

import java.text.NumberFormat;

public record ContaBancariaViews(ContaControllers conta) {
    public void exibirSaldo(){
        String usandoCheque = this.conta.getUsandoChequeEspecial() ? "Sim" : "Não";
        System.out.println("======================");
        System.out.println("Saldo da sua conta: " + NumberFormat.getCurrencyInstance().format(this.conta.getSaldo()));
        System.out.println("Cheque especial: " + NumberFormat.getCurrencyInstance().format(this.conta.getChequeEspecial()));
        System.out.println("Usando Cheque Especial? : " + usandoCheque);
        System.out.println("======================");

    }
    public void exibirChequeEspecial(){
        String usandoCheque = this.conta.getUsandoChequeEspecial() ? "Sim" : "Não";
        System.out.println("======================");
        System.out.println("Cheque especial: " + NumberFormat.getCurrencyInstance().format(this.conta.getChequeEspecial()));
        System.out.println("Usando Cheque Especial? : " + usandoCheque);
        System.out.println("======================");

    }
    public void exibirConta(){
        String usandoCheque = this.conta.getUsandoChequeEspecial() ? "Sim" : "Não";
        System.out.println("======================");
        System.out.println("Nome da Conta: " + this.conta.getNome());
        System.out.println("Saldo total da sua conta: " + NumberFormat.getCurrencyInstance().format(this.conta.getSaldo()));
        System.out.println("Dinheiro reservado para cheque especial: " + NumberFormat.getCurrencyInstance().format(this.conta.getChequeEspecial()));
        System.out.println("Usando Cheque Especial? : " + usandoCheque);
        System.out.println("======================");
    }
}
