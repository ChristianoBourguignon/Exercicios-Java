import java.io.*;
import java.util.Objects;


public class Agenda {
    String nome;
    String numero;
    String nomeAgenda;

    public Agenda(String nome, String numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public String getNome(){
        return this.nome;
    }
    public String getNumero(){
        return this.numero;
    }
    public String setNumero(String numero){
        return this.numero;
    }
    public static void escrita(Agenda agenda, String agendaNome){
        try {
            FileWriter fw = new FileWriter(agendaNome, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(agenda.getNome()+";"+agenda.getNumero());
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        };
    };
    public static void lerTodos() {
        try{
            FileReader fr = new FileReader("agenda.txt");
            BufferedReader br = new BufferedReader(fr);
            while(br.ready()){
                String aux = br.readLine();
                String [] dados = aux.split(";");
                if(dados.length>1) {
                    System.out.println("Nome: " + dados[0] + " - Telefone: " + dados[1]);
                }
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void lerContato(String nome) {
        try{
            FileReader fr = new FileReader("agenda.txt");
            BufferedReader br = new BufferedReader(fr);
            boolean encontrado = false;
            while(br.ready()) {
                String aux = br.readLine();
                String[] dados = aux.split(";");
                if(dados.length>1){
                    if (dados[0].equals(nome)){
                        if (!encontrado) {
                            System.out.println("Encontrado os seguintes contatos com o nome: " + nome);
                            encontrado = true;
                        }
                        System.out.println("Nome: " + dados[0] + " - Telefone: " + dados[1]);
                    }
                }
            }
            if (!encontrado){
                System.out.println("Contato " + nome +" não encontrado.");
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void alterarContato(String antNumero, String novoNumero){
        try {
            FileReader fr = new FileReader("agenda.txt");
            BufferedReader br = new BufferedReader(fr);
            boolean encontrado = false;
            FileWriter fw = new FileWriter("tempAgenda.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            String nome = "";
            while(br.ready()){
                String aux = br.readLine();
                String[] dados = aux.split(";");
                if (dados.length > 1) {
                    if (dados[1].equals(antNumero)) {
                        nome = dados[0];
                        encontrado = true;
                    } else {
                        bw.write(dados[0] + ";" + dados[1]);
                        bw.newLine();
                    }
                }
            }
            bw.close();
            fw.close();
            br.close();
            fr.close();
            if (encontrado){
                Agenda ctCorrecao = new Agenda(nome,novoNumero);
                Agenda.escrita(ctCorrecao,"tempAgenda.txt");
                File tempAgenda = new File("tempAgenda.txt");
                File agenda = new File("agenda.txt");
                if (agenda.delete()) {
                    if(!tempAgenda.renameTo(new File("agenda.txt"))) {
                        System.out.println("Erro ao renomear arquivo!");
                    } else {
                        tempAgenda.renameTo(new File("agenda.txt"));
                    }
                } else {
                    System.out.println("Arquivo de Agenda não deletado");
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        };
    };
    public static void excluirContato(String numero){
        try {
            FileReader fr = new FileReader("agenda.txt");
            BufferedReader br = new BufferedReader(fr);
            boolean encontrado = false;
            FileWriter fw = new FileWriter("tempAgendaExcluir.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            while(br.ready()){
                String aux = br.readLine();
                String[] dados = aux.split(";");
                if (dados.length > 1) {
                    if (!dados[1].equals(numero)) {
                        bw.write(dados[0] + ";" + dados[1]);
                        bw.newLine();
                    }
                }
            }
            bw.close();
            fw.close();
            br.close();
            fr.close();
            File tempAgenda = new File("tempAgendaExcluir.txt");
            File agenda = new File("agenda.txt");
            if (agenda.delete()) {
                if(!tempAgenda.renameTo(new File("agenda.txt"))) {
                    System.out.println("Erro ao renomear arquivo!");
                } else {
                    tempAgenda.renameTo(new File("agenda.txt"));
                }
            } else {
                System.out.println("Arquivo de Agenda não deletado");
            }


        } catch (IOException e) {
            System.out.println(e);
        };
    };
}


