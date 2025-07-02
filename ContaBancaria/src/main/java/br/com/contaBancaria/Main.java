package br.com.contaBancaria;

import br.com.contaBancaria.Controllers.ContaControllers;
import br.com.contaBancaria.Exception.DadosInvalidosException;
import br.com.contaBancaria.Exception.ExceptionCustom;
import br.com.contaBancaria.Exception.NoExistsAccountException;
import br.com.contaBancaria.Models.ContaBancaria;

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
            System.out.println("======================");
            switch (sc.nextInt()) {
                case 1:
                    try {
                        System.out.println("Digite o nome do conta: ");
                        String nome = sc.nextLine();
                        System.out.println("Digite o saldo do conta: ");
                        double saldo = sc.nextDouble();
                        System.out.println("Digite o cheque especial: ");
                        double chequeEspecial = sc.nextDouble();

                        if (nome.isEmpty() || saldo < 0 || chequeEspecial < 0) {
                            throw new DadosInvalidosException("Dados de cadastros inválidos");
                        }
                        conta = new ContaBancaria(nome, saldo, chequeEspecial);
                        cc = new ContaControllers(conta);
                    } catch (DadosInvalidosException e) {
                        throw new ExceptionCustom("Erro ao criar uma conta bancária: ",
                                new DadosInvalidosException(e.getMessage()));
                    }
                    break;
                case 2:
                    try {
                        if(conta != null) {
                            cc.consultarSaldo();
                        } else {
                            throw new NoExistsAccountException("Não existe conta criada");
                        }
                    } catch (NoExistsAccountException e) {
                        throw new ExceptionCustom("Erro ao consultar saldo: ",
                                new NoExistsAccountException(e.getMessage()));
                    }
                    break;
                case 3:
                    try {
                        if(conta == null) {throw new NoExistsAccountException("Não existe conta criada");}
                        System.out.println("Digite o valor que deseja adicionar: ");
                        cc.AdicionarSaldo(sc.nextDouble());
                    } catch (NoExistsAccountException e) {
                        throw new ExceptionCustom("Erro ao consultar saldo: ",
                                new NoExistsAccountException(e.getMessage()));
                    }
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Menu inválido, tente novamente.");
                    break;
            }

        }

    }
}