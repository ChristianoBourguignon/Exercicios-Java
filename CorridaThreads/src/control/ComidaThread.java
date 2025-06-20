package control;

import java.awt.Component;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ComidaThread extends JLabel implements Runnable {
    private Thread ComidaThread = null;
    private int posX;
    private int posY;
    private ImageIcon imagem;
    private static int pos = 0;
    private String nome;

    public ComidaThread(String nome, ImageIcon img, int posX, int posY) {
        super(img);
        this.imagem = img;
        this.posX = posX;
        this.posY = posY;
        this.nome = nome;
        this.ComidaThread = new Thread(this, nome);
        this.ComidaThread.start();
    }

    public void run() {
        this.posX += (new Random()).nextInt(3) * 10;
        this.setLocation(this.posX, this.posY);
        if (this.posX >= 1200) {
            ++pos;
            JOptionPane.showMessageDialog((Component)null, pos + ") " + this.nome);
        } else {
            try {
                Thread.sleep((new Random()).nextInt(2) * 500);
                this.run();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
