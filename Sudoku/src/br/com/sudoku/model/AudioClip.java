package br.com.sudoku.model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioClip {
    private Clip clip;

    public void carregar(String caminho) {
        try {
            File arquivo = new File(caminho);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(arquivo);

            clip = AudioSystem.getClip();
            clip.open(audioStream);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void tocar() {
        if (clip != null) {
            clip.setFramePosition(0); // volta ao início
            clip.start();
        }
    }

    public void parar() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}