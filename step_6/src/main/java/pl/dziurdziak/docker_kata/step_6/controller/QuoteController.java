package pl.dziurdziak.docker_kata.step_6.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dziurdziak.docker_kata.step_6.model.Quote;
import pl.dziurdziak.docker_kata.step_6.service.QuoteService;

/**
 * @author Mateusz Dziurdziak
 */
@RestController
@RequestMapping("/quote")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/random")
    public Quote getRandom() {
        return quoteService.getRandomQuote();
    }
}
