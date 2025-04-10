import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Pessoa p1 = new Pessoa("Christiano",23,"1234");
        try {
            FileOutputStream arqSer = new FileOutputStream("C:\\Users\\chris_i1piao2\\IdeaProjects\\Serializacao\\serial.ser");
            ObjectOutputStream oSer = new ObjectOutputStream(arqSer);
            oSer.writeObject(p1);
            oSer.close();
            p1.setTelefone("987");
            System.out.println(p1);
            System.out.println("\n====================================\n");
            FileInputStream arqDes = new FileInputStream("C:\\Users\\chris_i1piao2\\IdeaProjects\\Serializacao\\serial.ser");
            ObjectInputStream oDes = new ObjectInputStream(arqDes);
            p1 = (Pessoa)oDes.readObject();
            System.out.println(p1);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new IOException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}