import ca.wbac.callbag.sink.ForEach;
import ca.wbac.callbag.source.Range;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class e2eTest {
    private final Range range = new Range(0, 1000000);
    private final ForEach<Integer> forEach = new ForEach<>(System.out::println);

    @Test
    void test1() {
        range.greet(forEach);
    }

    @Test
    void stream() {
        IntStream.range(0, 1000000)
                .forEach(System.out::println);
    }
}
