package br.com.contaBancaria.Exception;

public class DepositInAccountException extends RuntimeException {
    public DepositInAccountException(String message) {
        super(message);
    }
}
