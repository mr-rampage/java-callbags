package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.basics.helpers.PullableSink;
import ca.wbac.callbags.basics.helpers.PullableSource;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ca.wbac.callbags.basics.operator.Operator.filter;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.lists;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FilterOperatorTest {
    @Test
    void should_consume_from_a_pullable_source() {
        qt()
                .forAll(lists().of(integers().all()).ofSizes(integers().between(0, 1000)))
                .check((List<Integer> integerList) -> {
                    final var processed = new ArrayList<Integer>();
                    final var source = new PullableSource<>(integerList);
                    final var sink = new PullableSink<>(processed);

                    source.pipe(filter(i -> i % 2 == 0)).pipe(sink);

                    return integerList.stream().filter(i -> i % 2 == 0).collect(Collectors.toList()).equals(processed);
                });
    }
}
