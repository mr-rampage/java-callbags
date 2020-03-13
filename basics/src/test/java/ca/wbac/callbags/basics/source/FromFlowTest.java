package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.basics.helpers.PushableSink;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.SubmissionPublisher;

import static ca.wbac.callbags.basics.source.Source.fromFlow;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.lists;

public class FromFlowTest {

    @Test
    void should_push_values_from_publisher() {
        qt()
                .forAll(lists().of(integers().all()).ofSizes(integers().between(0, 1000)))
                .check((List<Integer> integerList) -> {
                    final var publisher = new SubmissionPublisher<Integer>();
                    final var completableFuture = new CompletableFuture<Collection<Integer>>();

                    fromFlow(publisher).pipe(new PushableSink<>(integerList.size(), completableFuture));

                    integerList.forEach(publisher::submit);
                    publisher.close();

                    try {
                        return completableFuture.get().equals(integerList);
                    } catch (InterruptedException | ExecutionException e) {
                        return false;
                    }
                });
    }
}
