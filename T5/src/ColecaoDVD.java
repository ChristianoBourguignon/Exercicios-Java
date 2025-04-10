import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class DVD {
    private String titulo;
    private String genero;
    private int numeroCaixa;

    public DVD(String titulo, String genero, int numeroCaixa) {
        this.titulo = titulo;
        this.genero = genero;
        this.numeroCaixa = numeroCaixa;
    }

    public String getTitulo() { return titulo; }
    public String getGenero() { return genero; }
    public int getNumeroCaixa() { return numeroCaixa; }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public void setNumeroCaixa(int numeroCaixa) {
        this.numeroCaixa = numeroCaixa;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Gênero: " + genero + ", Caixa: " + numeroCaixa;
    }
}

public class ColecaoDVD {
    public static void main(String[] args) {
        List<DVD> colecao = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Incluir DVD");
            System.out.println("2. Alterar DVD");
            System.out.println("3. Excluir DVD");
            System.out.println("4. Listar DVDs");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: // Incluir
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Gênero: ");
                    String genero = scanner.nextLine();
                    System.out.print("Número da Caixa: ");
                    int caixa = scanner.nextInt();
                    colecao.add(new DVD(titulo, genero, caixa));
                    break;
                case 2: // Alterar
                    System.out.print("Título do DVD para alterar: ");
                    String tituloAlterar = scanner.nextLine();
                    for (DVD dvd : colecao) {
                        if (dvd.getTitulo().equalsIgnoreCase(tituloAlterar)) {
                            System.out.print("Novo título: ");
                            dvd.setTitulo(scanner.nextLine());
                            System.out.print("Novo gênero: ");
                            dvd.setGenero(scanner.nextLine());
                            System.out.print("Novo número da caixa: ");
                            dvd.setNumeroCaixa(scanner.nextInt());
                            scanner.nextLine();
                            break;
                        }
                    }
                    break;
                case 3: // Excluir
                    System.out.print("Título do DVD para excluir: ");
                    String tituloExcluir = scanner.nextLine();
                    colecao.removeIf(dvd -> dvd.getTitulo().
                            equalsIgnoreCase(tituloExcluir));
                    break;
                case 4: // Listar
                    Collections.sort(colecao,
                            Comparator.comparing(DVD::getGenero).
                                    thenComparing(DVD::getTitulo));
                    colecao.forEach(System.out::println);
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
