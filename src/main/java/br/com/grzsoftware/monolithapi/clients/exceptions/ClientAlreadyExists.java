package br.com.grzsoftware.monolithapi.clients.exceptions;

public class ClientAlreadyExists extends RuntimeException {
    public ClientAlreadyExists(String message) {
        super(message);
    }
}
