package br.com.sudoku;

import br.com.sudoku.ui.View;
import br.com.sudoku.util.LimitNumberInput;
import br.com.sudoku.model.SudokuCell;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public final static int BOARD_LIMIT = 9;

    public static void main(String[] args) {
        new View();
    }

}