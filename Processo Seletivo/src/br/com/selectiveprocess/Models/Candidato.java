package br.com.selectiveprocess.Models;

public class Candidato {
    private String nome;
    private String cpf;
    private Double SalarioPretendido;
    private int idade;

    public Candidato(String nome, String cpf, Double SalarioPretendido, int idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.SalarioPretendido = SalarioPretendido;
        this.idade = idade;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public Double getSalarioPretendido() {
        return SalarioPretendido;
    }
    public void setSalarioPretendido(Double SalarioPretendido) {
        this.SalarioPretendido = SalarioPretendido;
    }
    public int getIdade() {
        return this.idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

}
