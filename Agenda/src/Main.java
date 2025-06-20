import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Agenda ctChristiano = new Agenda("Pedro", "123");
        Agenda ctChristiano1 = new Agenda("Christiano", "1234");
        Agenda.escrita(ctChristiano,"agenda.txt");
        Agenda.escrita(ctChristiano1,"agenda.txt");
        //Agenda.lerTodos();
        //Agenda.lerContato(ctChristiano.getNome());
        Agenda.alterarContato(ctChristiano.getNumero(), "256985");
        //Agenda.lerTodos();
        Agenda.alterarContato(ctChristiano1.getNumero(), "221585");
        Agenda.excluirContato("221585");
        Agenda.lerTodos();
    }
}