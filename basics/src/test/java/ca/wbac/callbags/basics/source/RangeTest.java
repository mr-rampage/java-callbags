package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.basics.helpers.PullableSink;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ca.wbac.callbags.basics.source.Source.range;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;

public class RangeTest {
    @Test
    void should_pull_range_values() {
        qt()
                .forAll(integers().between(1, 1000), integers().between(1, 1000))
                .check((lowerBound, count) -> {
                    final var processed = new ArrayList<Integer>();
                    final var expected = IntStream.rangeClosed(lowerBound, lowerBound + count).boxed().collect(Collectors.toList());
                    range(lowerBound, lowerBound + count).pipe(new PullableSink<>(processed));
                    return expected.equals(processed);
                });
    }
}
