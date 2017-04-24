package pl.dziurdziak.docker_kata.step_5.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.dziurdziak.docker_kata.step_5.model.Quote;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Mateusz Dziurdziak
 */
@Service
public class QuoteService {

    public Quote getRandomQuote() {
        List<Quote> quotes = getQuotes();
        return quotes.get(new Random().nextInt(quotes.size()));
    }

    @SneakyThrows(IOException.class)
    private static List<Quote> getQuotes() {
        return Files.readAllLines(new File("/quotes/quotes.txt").toPath(), Charset.forName("UTF-8"))
                .stream()
                .filter(line -> !line.isEmpty())
                .filter(line -> !line.startsWith("#"))
                .map(QuoteService::lineToQuote)
                .collect(Collectors.toList());
    }

    private static Quote lineToQuote(String line) {
        String[] splittedLine = line.split("\\|");
        checkArgument(splittedLine.length == 2, "Invalid line: %s", line);
        return new Quote(splittedLine[0], splittedLine[1]);
    }
}
