public class Main {
    public static void main(String[] args) {
        try {
            // Criando funcionários
            Funcionario funcionario1 = new Funcionario(
                    "Christiano", 400.0);
            Funcionario funcionario2 = new Funcionario(
                    "Pedro", 350.0);
            Funcionario funcionario3 = new Funcionario(
                    "Patrick", 450.0);
            Funcionario funcionario4 = new Funcionario(
                    "Alexandre", 500.0);
            Funcionario funcionario5 = new Funcionario(
                    "Cristiane", 380.0);

            // Exibindo os dados dos funcionários criados manualmente
            System.out.println("Funcionario 1 - " +
                    "Matricula: " + funcionario1.getMatricula() +
                    ", Nome: " + funcionario1.getNome() +
                    ", Salario: " + funcionario1.getSalario());
            System.out.println("Funcionario 2 - " +
                    "Matricula: " + funcionario2.getMatricula() +
                    ", Nome: " + funcionario2.getNome() +
                    ", Salario: " + funcionario2.getSalario());
            System.out.println("Funcionario 3 - " +
                    "Matricula: " + funcionario3.getMatricula() +
                    ", Nome: " + funcionario3.getNome() +
                    ", Salario: " + funcionario3.getSalario());
            System.out.println("Funcionario 4 - " +
                    "Matricula: " + funcionario4.getMatricula() +
                    ", Nome: " + funcionario4.getNome() +
                    ", Salario: " + funcionario4.getSalario());
            System.out.println("Funcionario 5 - " +
                    "Matricula: " + funcionario5.getMatricula() +
                    ", Nome: " + funcionario5.getNome() +
                    ", Salario: " + funcionario5.getSalario());

            // Alterando o nome para dar o erro
            try {
                funcionario1.setNome("Este nome é muito " +
                        "longo e tem mais de quarenta " +
                        "caracteres!");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao definir o " +
                        "nome de christiano: " + e.getMessage());
            }

            // Tentativa de por um salário menor que o
            // mínimo
            try {
                funcionario2.setSalario(300.0);
            } catch (ViolacaoCltException e) {
                System.out.println("Erro ao definir o " +
                        "salário de Pedro: " + e);
            }

            // Tentativa de dar um aumento maior que 10%
            try {
                funcionario3.setSalario(600.0);
            } catch (AumentoAbsurdoException e) {
                System.out.println("Erro ao definir o " +
                        "salário de Patrick: " + e.getMessage());
            }

            // Tentativa de dar um aumento de 10%
            try {
                funcionario4.setSalario(550.0);
                System.out.println("Salário de Alexandre " +
                        "após aumento: " + funcionario4.getSalario());
            } catch (AumentoAbsurdoException e) {
                System.out.println("Erro ao definir o " +
                        "salário de Alexandre: " + e.getMessage());
            }

            // Tentativa de dar um aumento de 10%²
            try {
                funcionario5.setSalario(420.0);
                System.out.println("Salário de Cristiane " +
                        "após aumento: " + funcionario5.getSalario());
            } catch (AumentoAbsurdoException e) {
                System.out.println("Erro ao definir o " +
                        "salário de Cristiane: " + e.getMessage());
            }

            // Exibindo alterações
            System.out.println("Funcionario 1 - " +
                    "Matricula: " + funcionario1.getMatricula() +
                    ", Nome: " + funcionario1.getNome() +
                    ", Salario: " + funcionario1.getSalario());
            System.out.println("Funcionario 2 - " +
                    "Matricula: " + funcionario2.getMatricula() +
                    ", Nome: " + funcionario2.getNome() +
                    ", Salario: " + funcionario2.getSalario());
            System.out.println("Funcionario 3 - " +
                    "Matricula: " + funcionario3.getMatricula() +
                    ", Nome: " + funcionario3.getNome() +
                    ", Salario: " + funcionario3.getSalario());
            System.out.println("Funcionario 4 - " +
                    "Matricula: " + funcionario4.getMatricula() +
                    ", Nome: " + funcionario4.getNome() +
                    ", Salario: " + funcionario4.getSalario());
            System.out.println("Funcionario 5 - " +
                    "Matricula: " + funcionario5.getMatricula() +
                    ", Nome: " + funcionario5.getNome() +
                    ", Salario: " + funcionario5.getSalario());

        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
}
