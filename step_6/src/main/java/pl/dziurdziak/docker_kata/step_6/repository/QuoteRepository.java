package pl.dziurdziak.docker_kata.step_6.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.dziurdziak.docker_kata.step_6.model.Quote;

/**
 * @author Mateusz Dziurdziak
 */
public interface QuoteRepository extends CrudRepository<Quote, Long> {

    @Query(value = "select * from quote order by random() limit 1;", nativeQuery = true)
    Quote findRandom();

}
