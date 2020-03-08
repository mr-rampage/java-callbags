package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.basics.helpers.Sink;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static ca.wbac.callbags.basics.source.Source.interval;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;

public class IntervalTest {
    @Test
    void should_produce_for_a_sink() {
        qt()
                .withExamples(5)
                .forAll(integers().between(1, 100))
                .check(expected -> {
                    final CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
                    var fakeSink = new Sink<>(expected, completableFuture);
                    interval(1).greet(fakeSink);

                    try {
                        return completableFuture.get().equals(expected - 1);
                    } catch (InterruptedException | ExecutionException e) {
                        return false;
                    }
                });
    }
}
