package br.com.selectiveprocess.Exception;

public class ExceptionCustom extends Exception {
    public ExceptionCustom(String message, Exception cause) {
        super(message, cause);
    }
}
