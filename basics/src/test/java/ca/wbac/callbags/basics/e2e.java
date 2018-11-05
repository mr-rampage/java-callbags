package ca.wbac.callbags.basics;

import ca.wbac.callbags.basics.operator.Operator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static ca.wbac.callbags.basics.operator.Operator.*;
import static ca.wbac.callbags.basics.sink.Sink.forEach;
import static ca.wbac.callbags.basics.source.Source.range;
import static ca.wbac.callbags.basics.util.Utils.pipe;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class e2e {
    @Test
    @DisplayName("Should connect source to sink")
    void testConnection() {
        List<Integer> actual = new ArrayList<>();

        pipe(
                range(0, 10),
                Operator.<Integer>skip(2)
                        .andThen(map(x -> x * 2))
                        .andThen(scan((acc, x) -> acc + x, 0))
                        .andThen(filter(x -> x < 12))
                        .andThen(take(2))
                ,
                forEach(actual::add)
        );

        List<Integer> expected = List.of(4, 10);
        assertThat(actual, is(expected));
    }
}
