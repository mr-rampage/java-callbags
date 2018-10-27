import ca.wbac.callbag.sink.ForEach;
import ca.wbac.callbag.source.Range;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class e2eTest {
    final Range range = new Range(0, 1000000);
    final ForEach forEach = new ForEach(System.out::println);

    @Test
    public void test1() {
        range.greet(forEach);
    }

    @Test
    public void stream() {
        IntStream.range(0, 1000000)
                .forEach(System.out::println);
    }
}
