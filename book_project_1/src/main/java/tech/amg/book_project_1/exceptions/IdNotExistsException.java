package tech.amg.book_project_1.exceptions;

public class IdNotExistsException extends RuntimeException{
    public IdNotExistsException(String message) {
        super(message);
    }

    public IdNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdNotExistsException(Throwable cause) {
        super(cause);
    }
}
