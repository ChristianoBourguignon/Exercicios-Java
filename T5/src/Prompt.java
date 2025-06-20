import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Prompt {
    public static void main(String[] args) {
        List<String> nomes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String comando;

        System.out.println("Digite os comandos (use +, -, L): ");
        while (!(comando = scanner.nextLine()).equals("")) {
            if (comando.startsWith("+")) {
                String nome = comando.substring(1).trim();
                nomes.add(nome);
            } else if (comando.startsWith("-")) {
                String nome = comando.substring(1).trim();
                nomes.remove(nome); // Remove apenas a primeira ocorrência
            } else if (comando.equalsIgnoreCase("L")) {
                Collections.sort(nomes); // Ordena alfabeticamente
                for (int i = 0; i < nomes.size(); i++) {
                    System.out.println((i + 1) + ". " + nomes.get(i));
                }
            } else {
                System.out.println("Comando inválido!");
            }
        }
        scanner.close();
    }
}
