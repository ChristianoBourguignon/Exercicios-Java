package com.ricky.views;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ricky.controllers.Controllers;
import com.ricky.services.Requisicao;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Display extends JFrame implements ActionListener {
    private JButton btnConsultar;
    private JTextField inputBuscar;
    private JLabel lblBuscar;


    private ImageIcon iconImg = new ImageIcon("../resources/img/logo.png");


    public Display() {
        //Config da janela
        this.setLayout((LayoutManager)null);
        this.setSize(500, 500);
        this.setLocation(50, 50);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.white);
        this.setTitle("Consumo de API");
        this.setIconImage(iconImg.getImage());
        this.setVisible(true);

        // Config elementos
        this.btnConsultar = new JButton("Consultar");
        this.lblBuscar = new JLabel("Digite o nome do API:");
        this.inputBuscar = new JTextField();

        // Comportamento elementos
        this.btnConsultar.addActionListener(this);

        // Localizações
        this.lblBuscar.setBounds(150, 20, 190, 50);
        this.inputBuscar.setBounds(150,70,190,30);
        this.btnConsultar.setBounds(150, 110, 190, 50);


        // Add na tela
        this.add(this.inputBuscar);
        this.add(this.lblBuscar);
        this.add(btnConsultar);


        //Config botão
        this.btnConsultar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent btnClick) {
        btnConsultar.setEnabled(false);
        try {
            Requisicao requisicao = new Requisicao();
            String response = requisicao.getCharacter(Integer.parseInt(inputBuscar.getText()));
            JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
            Controllers controllers = new Controllers();
            controllers.ControllerExportPDF(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            btnConsultar.setEnabled(true);
        }
    }
}

