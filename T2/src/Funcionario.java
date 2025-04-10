// INTEGRANTES DO GRUPO
// Christiano Monteiro Bourguignon Leite
// Gabriel Povoa Ribeiro

public class Funcionario {
    private int matricula;
    private String nome;
    private double salario;

    public Funcionario(int matricula, String nome, double salario) {
        this.matricula = matricula;
        this.nome = nome;
        this.salario = salario;
    }

    // Método para calcular o INSS
    public double calcularInss() {
        if (salario <= 720.00) {
            return salario * 0.0765;
        } else if (salario <= 1200.00) {
            return salario * 0.09;
        } else if (salario <= 2400.00) {
            return salario * 0.11;
        }
        return 0;
    }

    // Exibir informações do funcionário
    public void exibirInformacoes() {
        System.out.println("Matrícula: " + matricula);
        System.out.println("Nome: " + nome );
        System.out.println("Salário: R$ " + salario);
        System.out.println("INSS a ser pago: R$ " + calcularInss());
        System.out.println();
    }

    public static void main(String[] args) {
        // Criando um array de 10 funcionários com dados manuais
        Funcionario[] funcionarios = new Funcionario[10];

        funcionarios[0] = new Funcionario(1, "Christiano", 2500.00);
        funcionarios[1] = new Funcionario(2, "Isaac", 1150.00);
        funcionarios[2] = new Funcionario(3, "Gabriel", 850.00);
        funcionarios[3] = new Funcionario(4, "Pedro", 950.00);
        funcionarios[4] = new Funcionario(5, "Patrick", 720.00);
        funcionarios[5] = new Funcionario(6, "Cristiane", 1300.00);
        funcionarios[6] = new Funcionario(7, "Alexandre", 2000.00);
        funcionarios[7] = new Funcionario(8, "Marcio Belo", 1800.00);
        funcionarios[8] = new Funcionario(9, "Mumuzinho", 2400.00);
        funcionarios[9] = new Funcionario(10, "Carlos", 700.00); // Nome adicional para completar o array

        // Exibindo as informações de cada funcionário e o INSS a ser pago
        for (Funcionario funcionario : funcionarios) {
            funcionario.exibirInformacoes();
        }
    }
}
