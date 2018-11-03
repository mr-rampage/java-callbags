package ca.wbac.callbags.basics;

import ca.wbac.callbags.basics.operator.Map;
import ca.wbac.callbags.basics.sink.ForEach;
import ca.wbac.callbags.basics.source.Range;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static ca.wbac.callbags.basics.util.Utils.pipe;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class e2e {
    @Test
    @DisplayName("Should connect source to sink")
    void testConnection() {
        List<Integer> actual = new ArrayList<>();

        pipe(
                new Range(0, 10),
                new Map<>(x -> x * 2),
                new ForEach<>(actual::add)
        );

        List<Integer> expected = List.of(0, 2, 4, 6, 8, 10, 12, 14, 16, 18);
        assertThat(actual, is(expected));
    }
}
