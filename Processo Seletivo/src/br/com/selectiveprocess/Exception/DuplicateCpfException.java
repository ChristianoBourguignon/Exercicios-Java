package br.com.selectiveprocess.Exception;

public class DuplicateCpfException extends RuntimeException {
    public DuplicateCpfException(String mensagem) {
        super(mensagem);
    }
}
