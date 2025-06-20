// INTEGRANTES DO GRUPO
// Christiano Monteiro Bourguignon Leite
// Gabriel Povoa Ribeiro

import java.util.Scanner;

public class ViacaoTartaruga {

    // Classe Veiculo com os atributos e métodos
    class Veiculo {
        private String origem;
        private String destino;
        private int capacidade;
        private int assentosVendidos;

        public Veiculo(String origem, String destino, int capacidade) {
            this.origem = origem;
            this.destino = destino;
            this.capacidade = capacidade;
            this.assentosVendidos = 0;
        }

        // Método para comprar um assento
        public boolean comprar() {
            if (assentosVendidos < capacidade) {
                assentosVendidos++;
                return true;
            } else {
                return false; // Não há mais assentos disponíveis
            }
        }

        // Retorna o número de assentos disponíveis
        public int getNumAssentosDisponiveis() {
            return capacidade - assentosVendidos;
        }

        // Retorna a capacidade total do veículo
        public int getNumAssentos() {
            return capacidade;
        }

        // Método para exibir as informações do veículo
        public void exibirInfo(int numero) {
            System.out.println(String.format("Cod: %02d | Frota: "+origem + "-" + destino + "Assentos: "+
                    getNumAssentosDisponiveis() + " até " + capacidade,numero));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ViacaoTartaruga viaTartaruga = new ViacaoTartaruga();

        // Criando a frota de 12 veículos
        Veiculo[] frota = new Veiculo[12];

        // Criando veículos
        frota[0] = viaTartaruga.new Veiculo("RJ", "SP", 20);
        frota[1] = viaTartaruga.new Veiculo("MG", "RS", 15);
        frota[2] = viaTartaruga.new Veiculo("MS", "RO", 21);
        frota[3] = viaTartaruga.new Veiculo("BA", "SE", 10);
        frota[4] = viaTartaruga.new Veiculo("SC", "RJ", 20);
        frota[5] = viaTartaruga.new Veiculo("AM", "AC", 60);
        frota[6] = viaTartaruga.new Veiculo("AL", "CE", 20);
        frota[7] = viaTartaruga.new Veiculo("RR", "GO", 45);
        frota[8] = viaTartaruga.new Veiculo("RN", "PE", 30);
        frota[9] = viaTartaruga.new Veiculo("SP", "BA", 40);
        frota[10] = viaTartaruga.new Veiculo("SC", "ES", 10);
        frota[11] = viaTartaruga.new Veiculo("PA", "MT", 30);

        int escolha = 9999;

        // Loop até o usuário escolher finalizar o programa (digitando 0)
        while (escolha != 0); {
            // Exibe a lista de ocupação dos veículos
            for (int i = 0; i < frota.length; i++) {
                frota[i].exibirInfo(i + 1);
            }

            // Pede o número do ônibus que o usuário deseja comprar um assento
            System.out.println("\nDigite o número do ônibus para comprar um assento (0 para sair): ");
            escolha = scanner.nextInt();

            if (escolha > 0 && escolha <= frota.length) {
                boolean sucesso = frota[escolha - 1].comprar();
                if (sucesso) {
                    System.out.println("Assento comprado com sucesso no ônibus " + escolha + "!\n");
                } else {
                    System.out.println("Ônibus " + escolha + " está lotado!\n");
                }
            }
        }

        for (int i = 0; i < frota.length; i++) {
            frota[i].exibirInfo(i + 1);
        }
        scanner.close();
    }
}
