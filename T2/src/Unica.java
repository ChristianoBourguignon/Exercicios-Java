// INTEGRANTES DO GRUPO
// Christiano Monteiro Bourguignon Leite
// Gabriel Povoa Ribeiro

public class Unica {

    // Atributo estático para armazenar a única instância da classe
    private static Unica instancia;

    // Construtor privado para evitar criação de novas instâncias
    private Unica() {
    }

    // Método público que retorna a única instância da classe
    public static Unica pegaInstancia() {
        if (instancia == null) {
            instancia = new Unica(); // Cria a instância
        }
        return instancia; // Retorna a instancia atual
    }

    public static void main(String[] args) {
        Unica u = Unica.pegaInstancia();
        Unica v = Unica.pegaInstancia();
        Unica x = Unica.pegaInstancia();

        // Deve retornar true, pois todas as variáveis estão referenciando a mesma instância
        System.out.println((u == v) && (x == v));
    }
}
