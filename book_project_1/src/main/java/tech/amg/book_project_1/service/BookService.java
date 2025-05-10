package tech.amg.book_project_1.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.amg.book_project_1.domian.dto.BookDTO;
import tech.amg.book_project_1.domian.entities.Book;
import tech.amg.book_project_1.exceptions.BookAlreadyExistsException;
import tech.amg.book_project_1.exceptions.BookNotFoundException;
import tech.amg.book_project_1.exceptions.IdNotExistsException;
import tech.amg.book_project_1.exceptions.TitleNotExistsException;
import tech.amg.book_project_1.mapper.BookMapper;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookMapper bookMapper;

    List<Book> books = new ArrayList();

    @Autowired
    public BookService(BookMapper bookMapper){
        this.bookMapper = bookMapper;
    }


    @PostConstruct
    private void initializeBooks() {
        books.addAll(List.of(
                new Book( "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 0),
                new Book( "To Kill a Mockingbird", "Harper Lee", "Fiction", 0),
                new Book( "1984", "George Orwell", "Dystopian", 0),
                new Book( "Pride and Prejudice", "Jane Austen", "Romance", 0),
                new Book( "The Catcher in the Rye", "J.D. Salinger", "Coming-of-age", 0)));
    }


    public List<Book> getBooks(String category){
        return category == null ? books : books.stream().filter(book -> book.getCategory().equalsIgnoreCase(category)).toList();
    }

    public Book getBookById(int id){
        return books.stream().filter(book -> book.getId() == id).findFirst().orElseThrow(
                ()-> new BookNotFoundException("id : "+id+" not exists !!")
        );
    }

    public Book getBookByTitle(String title){
        return books.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).findFirst().orElseThrow(
                ()-> new BookNotFoundException("There is no book with title : "+title)
        );
    }

    public void updateBook(BookDTO updateBookDTO){
        for (int i = 0; i < books.size(); i++) {
            long bookId = 0;
            if (books.get(i).getTitle().equalsIgnoreCase(updateBookDTO.getTitle())) {
                bookId = books.get(i).getId();
                Book book = bookMapper.bookDtoToBook(updateBookDTO);
                book.setId(bookId);
                books.set(i, book);
                return;
            }
        }
        throw new TitleNotExistsException("book with title : "+updateBookDTO.getTitle()+" not exists !!!");
    }

    public void createBook(BookDTO createdBookDTO){
        boolean isNewBook = books.stream().noneMatch(book -> book.getTitle().equalsIgnoreCase(createdBookDTO.getTitle()));
        if (isNewBook) {
            books.add(bookMapper.bookDtoToBook(createdBookDTO));
            return;
        }
        throw new BookAlreadyExistsException("Book with title : "+createdBookDTO.getTitle()+" is already exists !!");
    }

    public void deleteBookById(int id){
       if( books.removeIf(book -> book.getId() == id) == true)
           return;
       throw new IdNotExistsException("There is no book with ID : "+id + " to be deleted !!");
    }
}
