import br.com.selectiveprocess.Controllers.ProcessoSeletivoController;
import br.com.selectiveprocess.Exception.ExceptionCustom;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws ExceptionCustom {
        ProcessoSeletivoController ps = new ProcessoSeletivoController();
        Scanner input = new Scanner(System.in);
        int opcao;
        while(true){
            System.out.println("1 - Adicionar candidato");
            System.out.println("2 - Analisar candidatos");
            System.out.println("3 - Verificar candidato (por cpf)");
            System.out.println("4 - Excluir candidato");
            System.out.println("5 - Mostrar um candidato");
            System.out.println("6 - Mostrar todos os candidatos");
            System.out.println("0 - Sair");
            System.out.println("Digite as operações do processo seletivo:");
            opcao = input.nextInt();
            switch(opcao){
                case 1 -> ps.AdicionarCandidato();
                case 2 -> ps.analisarCandidatos();
                case 3 -> {
                    System.out.println("\nQual é o CPF do candidato que deseja buscar?");
                    String cpf = input.next();
                    ps.analisarCandidatoPorCpf(cpf);
                }
                case 4 -> {
                    System.out.println("\nQual é o CPF do candidato que deseja excluir?");
                    String cpf = input.next();
                    ps.excluirCandidato(cpf);
                    System.out.println("\nCandidato removido com sucesso\n");
                }
                case 5 -> {
                    System.out.println("\nQual é o CPF do candidato que você deseja exibir os dados?");
                    String cpf = input.next();
                    ps.mostrarDadosCandidato(cpf);
                }
                case 6 -> ps.mostrarTodosCandidatos();
                case 0 -> System.exit(0);
                default -> System.out.println("Numero inválido, veja as opções novamente!");
            }
        }
    }
}