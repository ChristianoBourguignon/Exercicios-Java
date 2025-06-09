package br.com.dio;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o seu nome: ");
        String nome = sc.nextLine();
        System.out.println(nome + ", por favor digite o agência da sua conta: ");
        String agencia = sc.nextLine();
        System.out.println(nome + ", por favor digite o numero da sua conta: ");
        int numero = sc.nextInt();
        System.out.println(nome + ", por favor digite o saldo da sua conta: ");
        double saldo = sc.nextDouble();
        sc.close();

        ContaTerminal c1 = new ContaTerminal(numero,agencia,nome,saldo);
        c1.exibirConta();
    }
}