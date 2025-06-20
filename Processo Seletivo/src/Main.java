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
            System.out.println("4 - Excluir candid1ato");
            System.out.println("5 - Mostrar candid1atos");
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
                    System.out.println("\nQual é o CPF do candidato que deseja buscar?");
                    String cpf = input.next();
                    ps.excluirCandidato(cpf);
                }
                case 5 -> {
                    ps.mostrarCandidatos();
                }
                case 0 -> System.exit(0);
                default -> System.out.println("Numero inválido, veja as opções novamente!");
            }
        }
    }
}