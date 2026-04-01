package dev.vinicius.exception;

public class EmailAlreadyTakenException extends RuntimeException {
    public EmailAlreadyTakenException() {
        super("Email already taken");
    }
}