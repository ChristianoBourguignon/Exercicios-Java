package br.com.sudoku.ui;

import br.com.sudoku.controllers.GameController;
import br.com.sudoku.model.GameStatusEnum;
import br.com.sudoku.ui.custom.Button.ExitGameButton;
import br.com.sudoku.ui.custom.Button.FinishGameButton;
import br.com.sudoku.ui.custom.Button.ResetGameButton;
import br.com.sudoku.ui.custom.Button.StartGameButton;
import br.com.sudoku.util.SudokuCell;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Map;

public class View extends JFrame implements ActionListener {

    private StartGameButton btnStart;
    private FinishGameButton btnFinish;
    private ExitGameButton btnExit;
    private ResetGameButton btnReset;
    private GameController gameBoard;

    public View(Map<JTextField, SudokuCell> sc) {
        this.gameBoard = new GameController(sc);

        this.setTitle("Sudoku");
        this.setSize(600, 800);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Painel do tabuleiro 9x9
        JPanel boardPanel = new JPanel(new GridLayout(9, 9));
        boardPanel.setBounds(50, 20, 500, 500);


        // Preencher as células
        this.gameBoard.getMap().entrySet().stream()
                .sorted(Comparator.comparingInt((Map.Entry<JTextField, SudokuCell> e) -> e.getValue().getRow())
                        .thenComparingInt(e -> e.getValue().getCol()))
                .forEach(entry -> boardPanel.add(entry.getKey()));

        // Botões
        btnStart = new StartGameButton();
        btnFinish = new FinishGameButton();
        btnExit = new ExitGameButton();
        btnReset = new ResetGameButton();

        btnStart.addActionListener(this);
        btnFinish.addActionListener(this);
        btnReset.addActionListener(this);
        btnExit.addActionListener(this);

        this.add(boardPanel);
        this.add(btnStart);
        this.add(btnFinish);
        this.add(btnReset);
        this.add(btnExit);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnStart) {
            JOptionPane.showMessageDialog(this, "Jogo iniciado!");
        }
        if(e.getSource() == btnFinish) {
            int optionFinishGame = JOptionPane.showConfirmDialog(
                    this,
                    "Você realmente quer finalizar o jogo?",
                    "Finalizar Jogo",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (optionFinishGame == JOptionPane.YES_OPTION) {
                GameStatusEnum status = gameBoard.finishGame();
                switch (status) {
                    case HAS_ERROR:
                        JOptionPane.showMessageDialog(this, "O jogo contém erros!");
                        break;
                    case INCOMPLETE:
                        JOptionPane.showMessageDialog(this, "O jogo está incompleto!");
                        break;
                    case COMPLETE:
                        JOptionPane.showMessageDialog(this, "Parabéns você completou o jogo!");
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "O jogo não foi iniciado.");
                        break;
                }
            }
        }
        if(e.getSource() == btnReset) {
            int resposta = JOptionPane.showConfirmDialog(
                    this,
                    "Você realmente quer resetar os valores inseridos?",
                    "Resetar Sudoku",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (resposta == JOptionPane.YES_NO_OPTION) {
                gameBoard.clearGame();
            }
        }
        if(e.getSource() == btnExit) {
            System.exit(0);
        }
    }
}
