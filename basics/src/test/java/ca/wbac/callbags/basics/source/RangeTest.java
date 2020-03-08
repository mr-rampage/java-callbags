package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.basics.helpers.Sink;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static ca.wbac.callbags.basics.source.Source.range;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;

public class RangeTest {
    @Test
    void should_produce_for_a_sink() {
        qt()
                .forAll(integers().between(1, 1000), integers().between(1, 1000))
                .check((lowerBound, count) -> {
                    final CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
                    var fakeSink = new Sink<>(count + 1, completableFuture);
                    range(lowerBound, lowerBound + count).accept(fakeSink);
                    try {
                        return completableFuture.get().equals(lowerBound + count);
                    } catch (InterruptedException | ExecutionException e) {
                        return false;
                    }
                });
    }
}
