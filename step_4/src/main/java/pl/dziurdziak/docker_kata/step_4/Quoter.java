package pl.dziurdziak.docker_kata.step_4;

import lombok.Data;
import lombok.SneakyThrows;
import sun.misc.Signal;

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
public class Quoter {

    private static volatile int sleep_time = 5000;

    private static volatile List<Quote> quotes;

    public static void main(String[] args) throws InterruptedException {
        sleep_time = Integer.parseInt(args[0]);
        checkArgument(sleep_time >= 500, "Sleep time is too short");

        registerSignalHandlers();
        quotes = getQuotes();
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

        Signal.handle(new Signal("HUP"), sig -> {
            System.out.println("Reloading quotes\n");
            quotes = getQuotes();
        });
    }

    @SneakyThrows(IOException.class)
    private static List<Quote> getQuotes() {
        return Files.readAllLines(new File("/quotes/quotes.txt").toPath(), Charset.forName("UTF-8"))
                .stream()
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
