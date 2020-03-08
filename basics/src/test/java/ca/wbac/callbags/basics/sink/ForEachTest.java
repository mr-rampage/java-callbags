package ca.wbac.callbags.basics.sink;

import ca.wbac.callbags.basics.helpers.PullableSource;
import ca.wbac.callbags.basics.helpers.PushableSource;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.lists;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ForEachTest {
    @Test
    void should_consume_from_a_pullable_source() {
        qt().forAll(lists().of(integers().all()).ofSizes(integers().between(0, 1000)))
                .check((List<Integer> integerList) -> {
                    final var processed = new ArrayList<Integer>();
                    Sink.<Integer, Object>forEach(processed::add)
                            .accept(PullableSource.of(integerList));
                    return processed.size() == integerList.size();
                });
    }

    @Test
    void should_consume_from_a_pushable_source() {
        qt()
                .withExamples(20)
                .forAll(lists().of(integers().all()).ofSizes(integers().between(0, 10)))
                .check((List<Integer> integerList) -> {
                    final CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
                    final var processed = new ArrayList<Integer>();

                    Sink.<Integer, Object>forEach(processed::add)
                            .accept(PushableSource.of(integerList, completableFuture));

                    try {
                        return completableFuture.get() == processed.size();
                    } catch (InterruptedException | ExecutionException e) {
                        return false;
                    }
                });
    }
}
