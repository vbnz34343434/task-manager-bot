package ru.liga.taskmanagerbot.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String error) {
        super(error);
    }
}
