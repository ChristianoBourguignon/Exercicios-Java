package br.com.sudoku.util;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class LimitNumberInput extends DocumentFilter{
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (isValid(fb, string)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        if (isValid(fb, text)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    private boolean isValid(FilterBypass fb, String text) throws BadLocationException {
        // permite apagar
        if (text.isEmpty()) return true;

        // só aceita 1 caractere
        if (text.length() > 1) return false;

        // só aceita números de 1 a 9
        if (!text.matches("[1-9]")) return false;

        // impede que digite mais de 1 dígito no campo
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        return currentText.isEmpty();
    }
}
