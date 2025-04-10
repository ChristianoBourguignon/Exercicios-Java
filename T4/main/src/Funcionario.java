public class Funcionario {
    private static int contadorMatricula = 0;

    private final int matricula;
    private String nome;
    private double salario;

    public Funcionario(String nome, double salario) {
        this.matricula = ++contadorMatricula;
        setNome(nome);
        setSalario(salario);
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() > 40) {
            throw new IllegalArgumentException("O nome " +
                    "não pode exceder 40 caracteres.");
        }
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double novoSalario) {
        if (novoSalario < 350.0) {
            throw new ViolacaoCltException("Salário menor" +
                    " que R$350.0, o que não é permitido.",
                    777);
        }
        if (this.salario != 0 && novoSalario > this.salario * 1.10) {
            throw new AumentoAbsurdoException("Aumento " +
                    "superior a 10% do salário anterior.");
        }
        this.salario = novoSalario;
    }
}