package dev.ambryn.discord.errors;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }
    public UnauthorizedException(String message, Throwable ex) {
        super(message, ex);
    }
}
