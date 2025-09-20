package br.com.sudoku.ui;

import br.com.sudoku.controllers.GameController;
import br.com.sudoku.model.AudioClip;
import br.com.sudoku.model.GameStatusEnum;
import br.com.sudoku.model.SudokuCell;
import br.com.sudoku.ui.custom.Button.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

import static br.com.sudoku.model.GameDifficultyEnum.*;
import static br.com.sudoku.model.GameStatusEnum.*;
import static java.util.Objects.isNull;

public class View extends JFrame implements ActionListener {

    private StartGameButton btnStart;
    private FinishGameButton btnFinish;
    private ResetGameButton btnReset;
    private ResetDifficultyGameButton btnResetGame;
    private GameController gameBoard;
    private AudioClip audio;
    private JPanel boardPanel;

    public View() {
        this.setTitle("Sudoku");
        this.setSize(600, 800);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.audio = new AudioClip();

        audio.carregar("");


        // Painel do tabuleiro 9x9
        this.boardPanel = new JPanel(new GridLayout(9, 9));
        boardPanel.setBounds(50, 20, 500, 500);
        if(isNull(gameBoard)) {
            JLabel messageNonStarted = new JLabel("Inicie o sudoku", SwingConstants.CENTER);
            messageNonStarted.setFont(new Font("Arial", Font.BOLD, 20));
            JLabel messageTipsNonStarted = new JLabel("Escolhendo a dificuldade no botão iniciar jogo!", SwingConstants.CENTER);
            messageTipsNonStarted.setFont(new Font("Arial", Font.ITALIC, 18));
            boardPanel.add(messageNonStarted);
            boardPanel.add(messageTipsNonStarted);
        }

        // Botões
        btnStart = new StartGameButton();
        btnFinish = new FinishGameButton();
        btnReset = new ResetGameButton();
        btnResetGame = new ResetDifficultyGameButton();

        btnStart.addActionListener(this);
        btnFinish.addActionListener(this);
        btnReset.addActionListener(this);
        btnResetGame.addActionListener(this);

        this.add(boardPanel);
        this.add(btnStart);
        this.add(btnFinish);
        this.add(btnResetGame);
        this.add(btnReset);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnStart) {
            String[] choices = {EASY.getLabel(), MEDIUM.getLabel(), HARD.getLabel()};
            int optionStartedGame = JOptionPane.showOptionDialog(
                    this,
                    "Escolha a dificuldade do jogo",
                    "Dificuldade",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    choices,
                    choices[0]
            );
            GameStatusEnum gse = null;
            if(!isNull(this.gameBoard)) {
                gse = this.gameBoard.checkStatus();
            } else {
                this.gameBoard = new GameController(optionStartedGame);
                gse = this.gameBoard.startedGame();
            }
            startGame(gse);
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
                        audio.tocar();
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
        if(e.getSource() == btnResetGame) {
            this.gameBoard.setGameStatus(STARTED);
            String[] choices = {EASY.getLabel(), MEDIUM.getLabel(), HARD.getLabel()};
            int optionStartedGame = JOptionPane.showOptionDialog(
                    this,
                    "Escolha a dificuldade do jogo",
                    "Dificuldade",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    choices,
                    choices[0]
            );
            this.gameBoard = new GameController(optionStartedGame);
            GameStatusEnum gse = gameBoard.startedGame();

            startGame(gse);
        }
    }
    public void startGame(GameStatusEnum gse) {
        if (STARTED.equals(gse) || NON_STARTED.equals(gse)) {
            this.boardPanel.removeAll();
            fillCells();
        } else if (IN_STARTED.equals(gse)) {
            JOptionPane.showMessageDialog(this, "O jogo já está iniciado!");
        } else {
            JOptionPane.showMessageDialog(this, "O jogo não está iniciado!");
        }
    }
    public void fillCells() {
        this.gameBoard.getMap().entrySet().stream()
        .sorted(Comparator.comparingInt((Map.Entry<JTextField, SudokuCell> e) -> e.getValue().getRow())
                .thenComparingInt(e -> e.getValue().getCol()))
        .forEach(entry -> this.boardPanel.add(entry.getKey()));
        this.boardPanel.revalidate();
        this.boardPanel.repaint();
    }
}
