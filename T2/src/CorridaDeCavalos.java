// INTEGRANTES DO GRUPO
// Christiano Monteiro Bourguignon Leite
// Gabriel Povoa Ribeiro

public class CorridaDeCavalos {

    class Cavalo {
        private String nome;
        private String avatar;
        private double distanciaPercorrida;

        public Cavalo(String nome) {
            this.nome = nome;
            this.distanciaPercorrida = 0;

            switch (nome) {
                case "pretinho":
                    this.avatar = "P>";
                    break;
                case "branquinho":
                    this.avatar = "B>";
                    break;
                case "malhadinho":
                    this.avatar = "M>";
                    break;
            }
        }

        public double correr() {
            double incremento = Math.random()*1;
            this.distanciaPercorrida += incremento;
            mostrar();
            return this.distanciaPercorrida;
        }

        private void mostrar() {
            int espacos = (int) distanciaPercorrida;
            System.out.print(nome + ": ");
            for (int i = 0; i < espacos; i++) {
                System.out.print(" ");
            }
            System.out.println(avatar);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CorridaDeCavalos corrida = new CorridaDeCavalos();

        // Criando os três cavalos
        Cavalo pretinho = corrida.new Cavalo("pretinho");
        Cavalo branquinho = corrida.new Cavalo("branquinho");
        Cavalo malhadinho = corrida.new Cavalo("malhadinho");

        // Variável para armazenar o vencedor
        Cavalo vencedor = null;

        // Loop da corrida, até um cavalo ganhar
        while (vencedor == null) {
            // Limpa o console
            for (int i = 0; i < 25; i++) {
                System.out.println();
            }

            // Faz os cavalos correrem
            double distanciaPretinho = pretinho.correr();
            double distanciaBranquinho = branquinho.correr();
            double distanciaMalhadinho = malhadinho.correr();

            // Verifica se algum cavalo atingiu ou ultrapassou 60
            if (distanciaPretinho >= 60) {
                vencedor = pretinho;
            } else if (distanciaBranquinho >= 60) {
                vencedor = branquinho;
            } else if (distanciaMalhadinho >= 60) {
                vencedor = malhadinho;
            }
        }

        // Exibe o cavalo vencedor
        System.out.println("\n" + vencedor.nome + " GANHOU!!!");
    }
}
