package pl.dziurdziak.docker_kata.step_5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dziurdziak.docker_kata.step_5.model.Quote;
import pl.dziurdziak.docker_kata.step_5.service.QuoteService;

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
