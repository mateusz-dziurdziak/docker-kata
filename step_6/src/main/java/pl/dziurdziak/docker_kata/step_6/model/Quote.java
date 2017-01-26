package pl.dziurdziak.docker_kata.step_6.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Mateusz Dziurdziak
 */
@Data
@NoArgsConstructor
@Entity
public final class Quote {
    @Id
    private Long id;
    private String quote;
    private String author;
}
