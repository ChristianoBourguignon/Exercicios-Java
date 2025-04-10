import java.util.*;

public class CasesDVD {
    public static void main(String[] args) {
        Map<Integer, List<DVD>> caixas = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar DVD");
            System.out.println("2. Listar por número da caixa");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: // Adicionar DVD
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Gênero: ");
                    String genero = scanner.nextLine();
                    System.out.print("Número da Caixa: ");
                    int caixa = scanner.nextInt();
                    scanner.nextLine();

                    DVD novoDVD = new DVD(titulo, genero, caixa);
                    caixas.computeIfAbsent(caixa,
                            k -> new ArrayList<>()).add(novoDVD);
                    break;
                case 2: // Listar por caixa
                    System.out.print("Número da caixa: ");
                    int numCaixa = scanner.nextInt();
                    scanner.nextLine();

                    List<DVD> dvdsNaCaixa =
                            caixas.getOrDefault(numCaixa,
                                    new ArrayList<>());
                    dvdsNaCaixa.forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
