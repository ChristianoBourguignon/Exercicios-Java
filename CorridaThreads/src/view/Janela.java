package view;

import control.ComidaThread;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


//Obtido maior parte online

public class Janela extends JFrame implements ActionListener {
    private JButton btnCorrida;
    private JButton btnCancel;
    private JLabel lblPista;
    private ImageIcon imgComida1;
    private ImageIcon imgComida2;
    private ImageIcon imgComida3;
    private ImageIcon pista;

    private ImageIcon iconImg = new ImageIcon("./src/imagens/icon.png");

    public Janela() {
        this.setLayout((LayoutManager)null);
        this.setSize(1280, 650);
        this.setLocation(50, 50);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.white);
        this.setTitle("Corrida de Threads");
        this.setIconImage(iconImg.getImage());
        this.pista = new ImageIcon("./src/imagens/pista.png");
        this.lblPista = new JLabel(this.pista);
        this.lblPista.setBounds(0, 0, 1280, 650);
        this.add(this.lblPista);
        this.lblPista.setBackground(Color.BLACK);
        this.btnCorrida = new JButton("Começar Corrida");
        this.btnCancel = new JButton("Parar Corrida");
        this.btnCorrida.setBounds(410, 550, 190, 50);
        this.btnCancel.setBounds(640, 550, 120, 50);
        this.imgComida1 = new ImageIcon("./src/imagens/morango.png");
        this.imgComida2 = new ImageIcon("./src/imagens/hamburguer.png");
        this.imgComida3 = new ImageIcon("./src/imagens/salada.png");
        this.lblPista.add(this.btnCorrida);
        this.lblPista.add(this.btnCancel);
        this.setVisible(true);
        this.btnCorrida.addActionListener(this);
        this.btnCancel.addActionListener(this);
        JPanel jPanel = new JPanel();
        jPanel.setSize(300, 70);
        jPanel.setLayout((LayoutManager)null);
        jPanel.setLocation(970, 510);
        jPanel.setVisible(true);
        this.repaint();
    }

    public JLabel JLabelComida(String nome, ImageIcon img, int posX, int posY) {
        ComidaThread comida = new ComidaThread(nome, img, posX, posY);
        comida.setSize(256, 141);
        comida.setVisible(true);
        this.add(comida);
        return comida;
    }

    public void actionPerformed(ActionEvent dispara) {
        if (dispara.getSource() == this.btnCorrida){
            this.lblPista.add(this.JLabelComida("Morango", this.imgComida1, 0, 50));
            this.lblPista.add(this.JLabelComida("Hamburguer", this.imgComida2, 0, 200));
            this.lblPista.add(this.JLabelComida("Salada", this.imgComida3, 0, 350));
            btnCorrida.setVisible(false);
        }

        if (dispara.getSource() == this.btnCancel) {
            System.exit(0);
        }

    }
}
