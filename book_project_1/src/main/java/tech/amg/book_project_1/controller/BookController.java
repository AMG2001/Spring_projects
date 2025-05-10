package tech.amg.book_project_1.controller;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tech.amg.book_project_1.domian.dto.BookDTO;
import tech.amg.book_project_1.domian.entities.Book;
import tech.amg.book_project_1.service.BookService;


@Tag(name = "Books endpoints",description = "Contain all operations that are related to books")
@RestController
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping
    public List<Book> getBooks(@Parameter(description = "Optional query parameter for filtering") @RequestParam(required = false) String category) {
        return bookService.getBooks(category);
    }


    @GetMapping("/book/{id}")
    public ResponseEntity<?> getBookByIndex(@Parameter(description = "Id of the book")@PathVariable int id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/book/title/{title}")
    public ResponseEntity<?> getBookByTitle(@Parameter(description = "title of the book that want to get")@PathVariable String title) {
        Book foundedBook = bookService.getBookByTitle(title);
        return ResponseEntity.ok(foundedBook);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody BookDTO newBook) {
        bookService.createBook(newBook);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@RequestBody BookDTO updatedBook) {
        bookService.updateBook(updatedBook);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("book/{id}")
    public void deleteBookById(@Parameter(description = "id of the book that you want to remove") @PathVariable int id) {
        bookService.deleteBookById(id);
    }
}
