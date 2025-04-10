package view;
import models.Calculo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


//Obtido maior parte online

public class Janela extends JFrame implements ActionListener {
    private JButton btnCalcular;
    private JButton btnLimpar;
    private JTextField inputNumero1;
    private JTextField inputNumero2;
    private JTextField inputResultado;
    private JComboBox<String> inputEquacao;
    private JLabel labelInput1;
    private JLabel labelInput2;
    private JLabel labelEquacao;
    private JLabel labelResultado;

    String[] inputEquacoes = {"+","-","/","*"};
    //private ImageIcon iconImg = new ImageIcon("");

    public Janela() {
        this.setLayout((LayoutManager)null);
        this.setSize(1280, 650);
        this.setLocation(50, 50);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.white);
        this.setTitle("Calculadora");
        //this.setIconImage(iconImg.getImage());

        // Buttons
        this.btnCalcular = new JButton("Calcular");
        this.btnLimpar = new JButton("Limpar Campos");

        // labels
        this.labelInput1 = new JLabel("Digite o 1º numero:");
        this.labelInput2 = new JLabel("Digite o 2º numero:");
        this.labelEquacao = new JLabel("Selecione a equação da operação");
        this.labelResultado = new JLabel("Resultado:");
        this.labelInput1.setBounds(20,70,150,30);
        this.labelInput2.setBounds(220,70,150,30);
        this.labelEquacao.setBounds(420,70,250,30);
        this.labelResultado.setBounds(620,70,150,30);

        // Inputs
        this.inputNumero1 = new JTextField();
        this.inputNumero2 = new JTextField();
        this.inputEquacao = new JComboBox<>(inputEquacoes);
        this.inputResultado = new JTextField();
        this.inputNumero1.setBounds(20, 100, 190, 50);
        this.inputNumero2.setBounds(220, 100, 190, 50);
        this.inputEquacao.setBounds(420, 100, 190, 50);
        this.inputResultado.setBounds(620, 100, 190, 50);
        this.btnCalcular.setEnabled(false);
        this.btnCalcular.setBounds(220, 200, 190, 50);
        this.btnLimpar.setBounds(420, 200, 190, 50);

        //Validação
        inputNumero2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String regex ="-?\\d+(\\.\\d+)?\n";
                if (inputNumero1.getText().matches("-?\\d+(\\.\\d+)?") && inputNumero2.getText().matches("-?\\d+(\\.\\d+)?")){
                    btnCalcular.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog((Component)null, "Digite apenas números válidos");
                    btnCalcular.setEnabled(false);
                    inputNumero1.setText("");
                    inputNumero2.setText("");
                }
            }
        });


        // Add na tela
        this.add(this.labelInput1);
        this.add(this.labelInput2);
        this.add(this.labelEquacao);
        this.add(this.labelResultado);
        this.add(this.inputNumero1);
        this.add(this.inputNumero2);
        this.add(this.inputEquacao);
        this.add(this.inputResultado);
        this.add(this.btnCalcular);
        this.add(this.btnLimpar);

        //Config inputs
        this.inputResultado.setEditable(false);
        this.btnCalcular.addActionListener(this);
        this.btnLimpar.addActionListener(this);

        // Visible e Config
        this.setVisible(true);
        JPanel jPanel = new JPanel();
        jPanel.setSize(300, 70);
        jPanel.setLayout((LayoutManager)null);
        jPanel.setLocation(970, 510);
        jPanel.setVisible(true);
        this.repaint();
    }

    public void actionPerformed(ActionEvent btn) {
        if (btn.getSource() == this.btnCalcular){
            String operacao = this.inputEquacao.getSelectedItem().toString();
            float numero1 = Float.parseFloat(this.inputNumero1.getText());
            float numero2 = Float.parseFloat(this.inputNumero2.getText());
            if (!operacao.equals("/")){
                int resultado = (int) new Calculo(numero1, numero2, operacao).getResultado();
                this.inputResultado.setText(String.valueOf(resultado));
            } else {
                Float resultado = new Calculo(numero1, numero2, operacao).getResultado();
                this.inputResultado.setText(String.valueOf(resultado));
            }
           
        }

        if (btn.getSource() == this.btnLimpar) {
            this.inputNumero1.setText("");
            this.inputNumero2.setText("");
            this.inputResultado.setText("");
        }

    }
}
