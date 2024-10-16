package krg.petr.otusru.converters;

import org.springframework.stereotype.Component;
import krg.petr.otusru.models.Author;

@Component
public class AuthorConverter {

    public String toString(Author author) {
        return "Id: %d, FullName: %s".formatted(author.getId(), author.getFullName());
    }
}