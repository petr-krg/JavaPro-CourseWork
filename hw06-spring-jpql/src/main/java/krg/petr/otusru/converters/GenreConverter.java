package krg.petr.otusru.converters;

import krg.petr.otusru.models.dtos.GenreDto;
import org.springframework.stereotype.Component;
import krg.petr.otusru.models.Genre;

@Component
public class GenreConverter {

    public String toString(Genre genre) {
        return "Id: %d, Name: %s".formatted(genre.getId(), genre.getName());
    }

    public String toString(GenreDto genre) {
        return "Id: %d, Name: %s".formatted(genre.getId(), genre.getName());
    }
}