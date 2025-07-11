package br.com.servicesmarketing.Exception;

public class ExceptionCustom extends RuntimeException {
    public ExceptionCustom(String message, Throwable cause) {
        super(message, cause);
    }
}
