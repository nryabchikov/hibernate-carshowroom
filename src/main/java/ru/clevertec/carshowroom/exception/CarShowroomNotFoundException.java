package ru.clevertec.carshowroom.exception;

public class CarShowroomNotFoundException extends RuntimeException {
    private CarShowroomNotFoundException(String message) {
        super(message);
    }

    public static CarShowroomNotFoundException byId(Long id) {
        return new CarShowroomNotFoundException(String.format("CarShowroom with id %d not found", id));
    }
}
