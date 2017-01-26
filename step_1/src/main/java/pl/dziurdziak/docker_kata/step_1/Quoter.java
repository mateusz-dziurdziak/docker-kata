package pl.dziurdziak.docker_kata.step_1;

import lombok.Data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Mateusz Dziurdziak
 */
public class Quoter {

    public static void main(String[] args) throws InterruptedException {
        List<Quote> quotes = getQuotes();
        checkArgument(!quotes.isEmpty(), "No quotes");

        Random random = new Random();

        while(true) {
            Quote randomQuote = quotes.get(random.nextInt(quotes.size()));
            System.out.println(randomQuote.getQuote() + "\n" + randomQuote.getAuthor() + "\n");
            Thread.sleep(5000);
        }
    }

    private static List<Quote> getQuotes() {
        InputStream is = Quoter.class.getClassLoader().getResourceAsStream("quotes.txt");
        return new BufferedReader(new InputStreamReader(is))
                .lines()
                .filter(line -> !line.isEmpty())
                .filter(line -> !line.startsWith("#"))
                .map(Quoter::lineToQuote)
                .collect(Collectors.toList());
    }

    private static Quote lineToQuote(String line) {
        String[] splittedLine = line.split("\\|");
        checkArgument(splittedLine.length == 2, "Invalid line: %s", line);
        return new Quote(splittedLine[0], splittedLine[1]);
    }

    @Data
    private static final class Quote {
        private final String quote;
        private final String author;
    }

}
