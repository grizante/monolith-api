package br.com.grzsoftware.monolithapi.exception;

public class ClientAlreadyExists extends RuntimeException {
    public ClientAlreadyExists(String message) {
        super(message);
    }
}
