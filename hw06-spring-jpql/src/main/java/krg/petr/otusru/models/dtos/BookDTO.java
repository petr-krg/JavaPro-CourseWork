package krg.petr.otusru.models.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// Не обращайте внимания

@Getter
@Setter
@RequiredArgsConstructor
public class BookDTO {

    private Long id;
    private String title;
    private String authorFullName;
    private Long genreId;
    private String genreName;
}