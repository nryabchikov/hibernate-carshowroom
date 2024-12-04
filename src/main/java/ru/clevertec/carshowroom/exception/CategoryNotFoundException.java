package ru.clevertec.carshowroom.exception;

public class CategoryNotFoundException extends RuntimeException {
    private CategoryNotFoundException(String message) {
        super(message);
    }

    public static CategoryNotFoundException byId(Long id) {
        return new CategoryNotFoundException(String.format("Category with id %d not found", id));
    }

    public static CategoryNotFoundException byDefault() {
        return new CategoryNotFoundException("Category not found");
    }
}
