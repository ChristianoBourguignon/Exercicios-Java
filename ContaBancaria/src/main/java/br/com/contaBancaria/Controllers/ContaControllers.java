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
    public void RetirarSaldo(BigDecimal valorParaRetirar) {
        BigDecimal saldoAtual = this.conta.getSaldo();

        // Verificação quanto pode tirar do saldo
        if (saldoAtual.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal valorRetiradoDoSaldo = valorParaRetirar.min(saldoAtual);
            this.conta.setSaldo(saldoAtual.subtract(valorRetiradoDoSaldo));
            valorParaRetirar = valorParaRetirar.subtract(valorRetiradoDoSaldo);
        }

        // Se ainda tiver valor, terá que usar o cheque especial
        if (valorParaRetirar.compareTo(BigDecimal.ZERO) > 0) {
            this.conta.setUsandoChequeEspecial(true);
            retirarSaldoDoChequeEspecial(valorParaRetirar);
        }
    }

    public void retirarSaldoDoChequeEspecial(BigDecimal saldo) {
        if(this.conta.getUsandoChequeEspecial()) {
            this.conta.setSaldo(new BigDecimal("0.00"));
            this.conta.setChequeEspecial(this.conta.getChequeEspecial().subtract(saldo));
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
