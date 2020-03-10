package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.basics.helpers.PullableSink;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static ca.wbac.callbags.basics.source.Source.fromIter;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.lists;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FromIterableTest {
    @Test
    void should_pull_list_values() {
        qt()
                .forAll(lists().of(integers().all()).ofSizes(integers().between(0, 1000)))
                .check((List<Integer> integerList) -> {
                    final var processed = new ArrayList<Integer>();
                    fromIter(integerList).accept(new PullableSink<>(processed));
                    return integerList.equals(processed);
                });
    }
}
