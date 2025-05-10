package tech.amg.book_project_1.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.amg.book_project_1.exceptions.*;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public BookErrorResponse handleBookNotFoundException(BookNotFoundException ex) {
        return new BookErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<BookErrorResponse> handleBookAlreadyExistsException(BookAlreadyExistsException ex) {
        return new BookErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TitleNotExistsException.class)
    public ResponseEntity<BookErrorResponse> handleTitleNotExistsException(TitleNotExistsException ex) {
        return new BookErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdNotExistsException.class)
    public ResponseEntity<BookErrorResponse> handleTitleNotExistsException(IdNotExistsException ex) {
        return new BookErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
