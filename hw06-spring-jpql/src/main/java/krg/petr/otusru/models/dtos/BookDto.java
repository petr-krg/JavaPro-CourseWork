package krg.petr.otusru.models.dtos;

import krg.petr.otusru.models.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BookDto {

    private Long id;

    private String title;

    private AuthorDto author;

    private List<GenreDto> genres;

    public BookDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = new AuthorDto(book.getAuthor());
        this.genres = book.getGenres().stream().map(GenreDto::new).toList();
    }
}