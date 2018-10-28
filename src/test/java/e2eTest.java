import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static ca.wbac.callbag.Utils.pipe;
import static ca.wbac.callbag.operator.Operators.map;
import static ca.wbac.callbag.sink.Pullers.forEach;
import static ca.wbac.callbag.source.Pullables.range;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Pipe Testing")
class e2eTest {
    @Test
    @DisplayName("should map over a range and consume")
    void shouldMap() {
        List<Integer> actual = new ArrayList<>();
        List<Integer> expected = List.of(0, 2, 4, 6, 8, 10, 12, 14, 16, 18);
        pipe(
                range(0, 10),
                map((Integer x) -> 2 * x),
                forEach((Consumer<Integer>) actual::add)
        );
        assertThat(actual, is(expected));
    }
}
