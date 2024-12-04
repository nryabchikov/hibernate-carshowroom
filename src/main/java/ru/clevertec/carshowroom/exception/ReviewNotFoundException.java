package ru.clevertec.carshowroom.exception;

public class ReviewNotFoundException extends RuntimeException {
    private ReviewNotFoundException(String message) {
        super(message);
    }

    public static ReviewNotFoundException byId(Long id) {
        return new ReviewNotFoundException(String.format("Review with id %d not found", id));
    }
}

