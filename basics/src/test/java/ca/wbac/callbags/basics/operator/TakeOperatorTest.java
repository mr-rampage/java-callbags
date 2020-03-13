package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.basics.helpers.PullableSink;
import ca.wbac.callbags.basics.helpers.PullableSource;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ca.wbac.callbags.basics.operator.Operator.take;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.lists;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TakeOperatorTest {
    @Test
    void should_consume_from_a_pullable_source() {
        qt()
                .forAll(lists().of(integers().all()).ofSizes(integers().between(0, 1000)))
                .check((List<Integer> integerList) -> {
                    int index = integerList.size() > 0 ? new Random().nextInt(integerList.size()) : 0;
                    final var processed = new ArrayList<Integer>();
                    final var source = new PullableSource<>(integerList);
                    final var sink = new PullableSink<>(processed);

                    source.pipe(take(index)).pipe(sink);

                    return integerList.subList(0, index).equals(processed);
                });
    }
}
