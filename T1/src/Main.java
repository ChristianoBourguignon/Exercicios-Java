//2. [PIRAMIDE] Escreva um programa que mostre no console
//uma pirâmide de números, com um número de linhas entre
//1 e 9, fornecido pelo usuário. Caso o número esteja fora do
//limite, mostrar mensagem de erro e solicitar novamente
//o número. Por exemplo, se o número de linhas for 4, o resultado
//será:
//         1
//        121
//       12321
//      1234321

//IMPORT
import java.util.Scanner;

public class Main {

    public static void GerarPiramide(int linha) {
        // Criar a pirâmide
        for (int i = 1; i <= linha; i++) {

            // Adiconando espaço do lado esquerdo
            for (int j = 1; j <= (linha - i); j++) {
                System.out.print(" ");
            }

            // Primeira metade dos números
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }

            // Segunda metade dos números
            for (int j = i - 1; j >= 1; j--) {
                System.out.print(j);
            }
            // Pular de linha
            System.out.println();
        }
    }
    public static void main(String[] args) {

    // Incluindo a função SCANNER
    Scanner scanner = new Scanner(System.in);
    int nmrLinhas = 0;

    while (nmrLinhas < 1 || nmrLinhas > 9){

        // Solicitando o número de linhas através do scanner
        System.out.print("Digite o número desejado: (Entre 1 a 9)");

        // Armazenando o número de linhas através do scanner
        nmrLinhas = scanner.nextInt();
    }
    GerarPiramide(nmrLinhas);
    scanner.close();
    }
}