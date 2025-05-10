package tech.amg.book_project_1.exceptions;

public class TitleNotExistsException extends RuntimeException{
    public TitleNotExistsException(String message) {
        super(message);
    }

    public TitleNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TitleNotExistsException(Throwable cause) {
        super(cause);
    }
}
