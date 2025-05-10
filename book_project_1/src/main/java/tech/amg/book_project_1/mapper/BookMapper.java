package tech.amg.book_project_1.mapper;

import org.mapstruct.*;
import tech.amg.book_project_1.domian.dto.BookDTO;
import tech.amg.book_project_1.domian.entities.Book;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

    Book bookDtoToBook(BookDTO bookDTO);

    BookDTO bookToBookDto(Book book);

    @AfterMapping
    default void afterMappingBookDtoToBook(BookDTO bookDTO, @MappingTarget Book book) {
        book.setId(Book.lastIdValue++);
    }
}
