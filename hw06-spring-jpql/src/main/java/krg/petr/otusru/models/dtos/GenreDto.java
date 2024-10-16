package krg.petr.otusru.models.dtos;


import krg.petr.otusru.models.Genre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreDto {

    private Long id;

    private String name;

    public GenreDto(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }
}