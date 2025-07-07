package br.com.contaBancaria.Controllers;

import br.com.contaBancaria.Exception.*;
import br.com.contaBancaria.Models.ContaBancaria;
import br.com.contaBancaria.Views.ContaBancariaViews;

import java.math.BigDecimal;

public class ContaControllers {
    private final ContaBancaria conta;

    public ContaControllers(ContaBancaria conta) {
        if (conta == null) {
            throw new NoExistsAccountException("A conta não existe!");
        }
        this.conta = conta;
    }

    public void consultarSaldo() {
        ContaBancariaViews cbv = new ContaBancariaViews(this);
        cbv.exibirSaldo();
    }
    public void consultarChequeEspecial() {
        ContaBancariaViews cbv = new ContaBancariaViews(this);
        cbv.exibirChequeEspecial();
    }
    public void adicionarSaldo(BigDecimal saldo) {
        if (saldo == null || saldo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DadosInvalidosException("Valor para adicionar deve ser maior que zero.");
        }
        if (this.conta.getUsandoChequeEspecial()) {
//            Caso o limite de cheque especial seja usado, assim que possível a conta deve cobrar
//            uma taxa de 20% do valor usado do cheque especial
            BigDecimal limite = this.conta.getChequeEspecial();
            BigDecimal limiteTotal = this.conta.getLimitCheque();

            BigDecimal valorUtilizadoDoCheque = limiteTotal.subtract(limite);

            BigDecimal valorParaRepor = saldo.min(valorUtilizadoDoCheque);
            this.conta.setChequeEspecial(limite.add(valorParaRepor));

            BigDecimal restante = saldo.subtract(valorParaRepor);
            if (restante.compareTo(BigDecimal.ZERO) > 0) {
                this.conta.setSaldo(this.conta.getSaldo().add(restante));
            }

            if (this.conta.getChequeEspecial().compareTo(limiteTotal) == 0) {
                if(this.getSaldo().compareTo(limiteTotal) > 0) {
                    BigDecimal juros = saldo.multiply(new BigDecimal("0.2"));
                    this.conta.setSaldo(getSaldo().subtract(juros));
                }
                this.conta.setChequeEspecial(limiteTotal);
                this.conta.setUsandoChequeEspecial(false);
            }
        } else {
            this.conta.setSaldo(this.conta.getSaldo().add(saldo));
        }
    }
    public void retirarSaldo(BigDecimal valorParaRetirar) {

        if (valorParaRetirar == null || valorParaRetirar.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DadosInvalidosException("Valor para retirada deve ser maior que zero.");
        }

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

    public void retirarSaldoDoChequeEspecial(BigDecimal valorParaRetirar) {
        BigDecimal chequeEspecial = this.conta.getChequeEspecial();
        if (chequeEspecial.compareTo(valorParaRetirar) < 0) {
            throw new InsufficientFundsException("Saldo insuficiente.");
        }
        this.conta.setChequeEspecial(chequeEspecial.subtract(valorParaRetirar));
        if(this.conta.getChequeEspecial().compareTo(BigDecimal.ZERO) == 0){
            this.conta.setUsandoChequeEspecial(false);
        }
    }

    public void pagarBoleto(BigDecimal valorDoBoleto) {
        try {
            retirarSaldo(valorDoBoleto);
        } catch (InsufficientFundsException | DadosInvalidosException | IllegalArgumentException ex) {
            throw new ExceptionCustom("Erro ao realizar o pagamento do boleto: ", ex);
        }
    }
    public void consultarConta() {
        ContaBancariaViews cbv = new ContaBancariaViews(this);
        cbv.exibirConta();
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
    public String getNome(){return this.conta.getNome();}
}
