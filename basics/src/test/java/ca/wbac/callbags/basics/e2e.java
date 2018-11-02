package ca.wbac.callbags.basics;

import ca.wbac.callbags.basics.sink.Pullers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static ca.wbac.callbags.basics.operator.Operators.map;
import static ca.wbac.callbags.basics.source.Pullables.range;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class e2e {
    @Test
    @DisplayName("Should connect source to sink")
    void testConnection() {
        List<Integer> actual = new ArrayList<>();

        Pullers.<Integer>forEach(actual::add)
                .accept(map((Integer x) -> x * 2)
                        .apply(range(0, 10)));

        List<Integer> expected = List.of(0, 2, 4, 6, 8, 10, 12, 14, 16, 18);
        assertThat(actual, is(expected));
    }
}
