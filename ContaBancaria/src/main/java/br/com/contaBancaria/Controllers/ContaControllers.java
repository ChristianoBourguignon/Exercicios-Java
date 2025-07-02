package br.com.contaBancaria.Controllers;

import br.com.contaBancaria.Models.ContaBancaria;
import br.com.contaBancaria.Views.ContaBancariaViews;

public class ContaControllers {
    private final ContaBancaria conta;

    public ContaControllers(ContaBancaria conta) {
        this.conta = conta;
    }

    public void consultarSaldo() {
        ContaBancariaViews cbv = new ContaBancariaViews(conta);
        cbv.exibirSaldo();
    }
    public void AdicionarSaldo(double saldo) {
        this.conta.setSaldo(this.conta.getSaldo() + saldo);
    }
}
