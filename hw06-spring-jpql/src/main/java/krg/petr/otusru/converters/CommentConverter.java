package krg.petr.otusru.converters;

import krg.petr.otusru.models.Comment;
import org.springframework.stereotype.Component;


@Component
public class CommentConverter {

    public String toString(Comment comment) {
        return "Id: %d, Book title: %s, Comment text: %s".formatted(comment.getId(),
                comment.getBook().getTitle(),
                comment.getText());
    }
}