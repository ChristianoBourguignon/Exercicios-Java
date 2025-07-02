package br.com.contaBancaria.Controllers;

import br.com.contaBancaria.Models.ContaBancaria;
import br.com.contaBancaria.Views.ContaBancariaViews;

import java.math.BigDecimal;
import java.util.function.Supplier;

public class ContaControllers {
    private final ContaBancaria conta;

    public ContaControllers(ContaBancaria conta) {
        this.conta = conta;
    }

    public void consultarSaldo() {
        ContaBancariaViews cbv = new ContaBancariaViews(conta);
        cbv.exibirSaldo();
    }
    public void AdicionarSaldo(BigDecimal saldo) {

        this.conta.setSaldo(this.conta.getSaldo().add(saldo));
    }
    public void RetirarSaldo(BigDecimal saldo) {
        this.conta.setSaldo(this.conta.getSaldo().subtract(saldo));
        if (!temSaldoPositivo()) {
            this.conta.setUsandoChequeEspecial(true);
        }
    }
    public BigDecimal getSaldo() {
        return this.conta.getSaldo();
    }
    public BigDecimal getChequeEspecial(){
        return this.conta.getChequeEspecial();
    }
    public BigDecimal getSaldoTotal() {
        return this.conta.getSaldoTotal();
    }
    public boolean temSaldoPositivo() {
        return this.conta.getSaldo().compareTo(BigDecimal.ZERO) >= 0;
    }
    public boolean getUsandoChequeEspecial(){
        return this.conta.getUsandoChequeEspecial();
    }
}
