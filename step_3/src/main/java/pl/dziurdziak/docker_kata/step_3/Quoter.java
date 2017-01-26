package pl.dziurdziak.docker_kata.step_3;

import lombok.Data;
import sun.misc.Signal;

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

    private static volatile int sleep_time = 5000;

    public static void main(String[] args) throws InterruptedException {
        sleep_time = Integer.parseInt(args[0]);
        checkArgument(sleep_time >= 500, "Sleep time is too short");

        registerSignalHandlers();
        List<Quote> quotes = getQuotes();
        checkArgument(!quotes.isEmpty(), "No quotes");

        Random random = new Random();

        while (true) {
            Quote randomQuote = quotes.get(random.nextInt(quotes.size()));
            System.out.println(randomQuote.getQuote() + "\n" + randomQuote.getAuthor() + "\n");
            Thread.sleep(sleep_time);
        }
    }

    private static void registerSignalHandlers() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Ehh....");
            }
        });

        Signal.handle(new Signal("INT"), sig -> {
            System.out.println("You gonna regret this!");
            if (sleep_time < 500) {
                System.exit(0);
            }
            sleep_time = sleep_time / 2;
        });
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
