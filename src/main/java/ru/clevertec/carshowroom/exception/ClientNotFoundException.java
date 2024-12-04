package ru.clevertec.carshowroom.exception;

public class ClientNotFoundException extends RuntimeException {
    private ClientNotFoundException(String message) {
        super(message);
    }

    public static ClientNotFoundException byId(Long id) {
        return new ClientNotFoundException(String.format("Client with id %d not found", id));
    }
}

