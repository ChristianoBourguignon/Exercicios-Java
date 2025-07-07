package br.com.contaBancaria;

import br.com.contaBancaria.Controllers.ContaControllers;
import br.com.contaBancaria.Exception.DadosInvalidosException;
import br.com.contaBancaria.Exception.ExceptionCustom;
import br.com.contaBancaria.Exception.NoExistsAccountException;
import br.com.contaBancaria.Models.ContaBancaria;

import java.math.BigDecimal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ContaBancaria conta = null;
        ContaControllers cc = null;
        while (true) {
            System.out.println("======================");
            System.out.println("1 - Cadastrar Conta");
            System.out.println("2 - Consultar Conta");
            System.out.println("3 - Adicionar dinheiro");
            System.out.println("4 - Retirar dinheiro");
            System.out.println("5 - Consultar Saldo");
            System.out.println("6 - Consultar Cheque Especial");
            System.out.println("7 - Pagar Boleto");
            System.out.println("0 - Sair");
            System.out.println("======================");
            System.out.print("Digite sua opção:");
            switch (sc.nextInt()) {
                case 1:
                    try {
                        sc.nextLine();
                        System.out.println("Digite o nome do conta: ");
                        String nome = sc.nextLine();
                        System.out.println("Digite o saldo do conta: ");
                        BigDecimal saldo = sc.nextBigDecimal();

                        if (nome.isEmpty() || saldo.compareTo(BigDecimal.ZERO) < 0 ) {
                            throw new DadosInvalidosException("Dados de cadastros inválidos!");
                        }
                        conta = new ContaBancaria(nome, saldo);
                        cc = new ContaControllers(conta);
                    } catch (DadosInvalidosException e) {
                        throw new ExceptionCustom("Erro ao criar uma conta bancária: ",
                                new DadosInvalidosException(e.getMessage()));
                    }
                    break;
                case 2:
                    verificarConta(conta);
                    cc.consultarConta();
                    break;
                case 3:
                    verificarConta(conta);
                    System.out.println("Digite o valor que deseja adicionar: ");
                    cc.adicionarSaldo(sc.nextBigDecimal());
                    break;
                case 4:
                    verificarConta(conta);
                    System.out.println("Digite o valor que deseja retirar: ");
                    BigDecimal retirar = sc.nextBigDecimal();
                    cc.retirarSaldo(retirar);
                    cc.consultarSaldo();
                    break;
                case 5:
                    verificarConta(conta);
                    cc.consultarSaldo();
                    break;
                case 6:
                    verificarConta(conta);
                    cc.consultarChequeEspecial();
                    break;
                case 7:
                    verificarConta(conta);
                    System.out.println("Digite o valor do boleto: ");
                    cc.pagarBoleto(sc.nextBigDecimal());
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        }
    }
    public static void verificarConta(ContaBancaria conta){
        if (conta == null) {
            throw new ExceptionCustom("Erro ao executar a operação: ", 
                    new NoExistsAccountException("Conta não criada!"));
        }
    }
}