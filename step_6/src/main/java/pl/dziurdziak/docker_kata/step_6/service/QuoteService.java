package pl.dziurdziak.docker_kata.step_6.service;

import org.springframework.stereotype.Service;
import pl.dziurdziak.docker_kata.step_6.model.Quote;
import pl.dziurdziak.docker_kata.step_6.repository.QuoteRepository;

/**
 * @author Mateusz Dziurdziak
 */
@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote getRandomQuote() {
        return quoteRepository.findRandom();
    }
}
