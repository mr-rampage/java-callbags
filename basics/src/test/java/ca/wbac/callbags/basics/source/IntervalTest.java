package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.basics.helpers.PushableSink;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ca.wbac.callbags.basics.source.Source.interval;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;

public class IntervalTest {
    @Test
    void should_push_tick_values() {
        qt()
                .withExamples(5)
                .forAll(integers().between(1, 1000))
                .check(messages -> {
                    final var completableFuture = new CompletableFuture<Collection<Integer>>();
                    final var fakeSink = new PushableSink<>(messages, completableFuture);
                    final var expected = IntStream.range(0, messages).boxed().collect(Collectors.toList());
                    interval(1).accept(fakeSink);
                    try {
                        return completableFuture.get().equals(expected);
                    } catch (InterruptedException | ExecutionException e) {
                        return false;
                    }
                });
    }
}
