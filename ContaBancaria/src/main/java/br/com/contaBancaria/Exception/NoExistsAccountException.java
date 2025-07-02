package br.com.contaBancaria.Exception;

public class NoExistsAccountException extends RuntimeException {
    public NoExistsAccountException(String message) {
        super(message);
    }
}
