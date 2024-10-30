package krg.petr.otusru.converters;

import krg.petr.otusru.models.dtos.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import krg.petr.otusru.models.Book;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BookConverter {

    private final AuthorConverter authorConverter;

    private final GenreConverter genreConverter;

    public String toString(Book book) {
            var genresString = book.getGenres().stream()
                    .map(genreConverter::toString)
                    .map("{%s}"::formatted)
                    .collect(Collectors.joining(", "));

            return "Id: %d, title: %s, author: {%s}, genres: [%s]".formatted(
                    book.getId(),
                    book.getTitle(),
                    authorConverter.toString(book.getAuthor()),
                    genresString);
    }

    public String toString(BookDto book) {
        var genresString = book.getGenres()
                .stream()
                .map(genreConverter::toString)
                .map("{%s}"::formatted)
                .collect(Collectors.joining(", "));

        return "Id: %d, title: %s, author: %s, genres: [%s]".formatted(
                book.getId(),
                book.getTitle(),
                book.getAuthor().getFullName(),
                genresString);

    }

    public BookDto toDto(Book book) {
        return new BookDto(book);
    }
}