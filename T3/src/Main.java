import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cesta cesta = new Cesta();
        int opcao = 0;

        while (opcao != 4) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar o item");
            System.out.println("2. Mostrar Cesta");
            System.out.println("3. Limpar Cesta");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Tipos de " +
                            "produtos: 1-Banana, 2-Maçã, " +
                            "3-Morango, 4-Limão, 5-Uva, " +
                            "6-Pera, 7-Mamão");
                    System.out.print("Escolha o tipo de " +
                            "produto: ");
                    int tipoProduto = scanner.nextInt();
                    System.out.print("Digite a " +
                            "quantidade: ");
                    int quantidade = scanner.nextInt();

                    Produto produto = null;
                    switch (tipoProduto) {
                        case 1:
                            produto =
                                    new Banana(quantidade);
                            break;
                        case 2:
                            produto = new Maca(quantidade);
                            break;
                        case 3:
                            produto =
                                    new Morango(quantidade);
                            break;
                        case 4:
                            produto =
                                    new Limao(quantidade);
                            break;
                        case 5:
                            produto =
                                    new Uva(quantidade);
                            break;
                        case 6:
                            produto =
                                    new Pera(quantidade);
                            break;
                        case 7:
                            produto =
                                    new Mamao(quantidade);
                            break;
                        default:
                            System.out.println("Tipo" +
                                " de produto inválido.");
                            continue;
                    }
                    cesta.adicionarItem(produto);
                    break;
                case 2:
                    cesta.listarCesta();
                    break;

                case 3:
                    cesta.limparCesta();
                    break;
                case 4:
                    System.out.println("Programa " +
                            "Finalizado");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }
}
