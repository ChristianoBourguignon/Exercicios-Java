package br.com.dio;

//Atributo	Tipo	Exemplo
//Numero	Inteiro	1021
//Agencia	Texto	067-8
//Nome Cliente	Texto	MARIO ANDRADE
//Saldo	Decimal	237.48
public class ContaTerminal {
    private int numero;
    private String agencia;
    private String nome;
    private double saldo;

    public ContaTerminal(int numero, String agencia, String nome, double saldo) {
        this.numero = numero;
        this.agencia = agencia;
        this.nome = nome;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getAgencia() {
        return agencia;
    }
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public void exibirConta() {
        String nome = this.getNome();
        double valor = this.getSaldo();
        String agencia = this.getAgencia();
        int numero = this.getNumero();
        System.out.printf("Olá %s, obrigado por criar uma conta em nosso banco, sua agência é %s, conta %d e seu saldo %.2f já está disponível para saque.",nome,agencia,numero,saldo);
    }
}
