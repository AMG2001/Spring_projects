package tech.amg.book_project_1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import tech.amg.book_project_1.entities.Book;


@RestController
@RequestMapping("api/books")
public class BookController {

    List<Book> books = new ArrayList();

    @PostConstruct
    private void initializeBooks() {
        books.addAll(List.of(
                new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 0),
                new Book(2, "To Kill a Mockingbird", "Harper Lee", "Fiction", 0),
                new Book(3, "1984", "George Orwell", "Dystopian", 0),
                new Book(4, "Pride and Prejudice", "Jane Austen", "Romance", 0),
                new Book(5, "The Catcher in the Rye", "J.D. Salinger", "Coming-of-age", 0)));
    }

    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String category) {
       return category == null ? books : books.stream().filter(book -> book.getCategory().equalsIgnoreCase(category)).toList();
    }


    @GetMapping("/book/{id}")
    public ResponseEntity<?> getBookByIndex(@PathVariable int id) {
        Optional<Book> returnedBook = books.stream().filter(book -> book.getId() == id).findFirst();
        return returnedBook.isPresent() ?
                ResponseEntity.ok(returnedBook.get()) :
                ResponseEntity.badRequest().body("Requested Index is not available !!");
    }

    @GetMapping("/book/title/{title}")
    public ResponseEntity<?> getBookByTitle(@PathVariable String title) {
        Optional<Book> foundedBook = books.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).findFirst();
        return foundedBook.isPresent() ? ResponseEntity.ok(foundedBook.get()) : ResponseEntity.ok("There is no book with this title !!");
    }

    @PostMapping
    public String createBook(@RequestBody Book newBook){
        boolean isNewBook = books.stream().noneMatch(book -> book.getId() == newBook.getId());
        if(isNewBook){
            books.add(newBook);
            return "book added successfully !!";
        }
        return "This book is already exists !!";
    }

    @PutMapping
    public String updateBook(@RequestBody Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == updatedBook.getId()) {
                books.set(i,updatedBook);
                return "book updated successfully !!";
            }
        }
        return "There is no book to be updated !!";
    }


    @DeleteMapping("book/{id}")
    public String deleteBookById(@PathVariable int id){
       boolean isDeleted =  books.removeIf(book -> book.getId()==id);
        return isDeleted ? "book deleted successfully !!" : "There is no book with this id !!";
    }
}
