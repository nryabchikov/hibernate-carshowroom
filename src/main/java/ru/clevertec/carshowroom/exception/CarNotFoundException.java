package ru.clevertec.carshowroom.exception;

public class CarNotFoundException extends RuntimeException {
    private CarNotFoundException(String message) {
        super(message);
    }

    public static CarNotFoundException byId(Long id) {
        return new CarNotFoundException(String.format("Car with id %d not found", id));
    }
}
