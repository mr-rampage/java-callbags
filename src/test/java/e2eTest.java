import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static ca.wbac.callbag.sink.Pullers.forEach;
import static ca.wbac.callbag.source.Pullables.range;

class e2eTest {
    @Test
    void test1() {
        range(0, 1000000)
            .greet(forEach(System.out::println));
    }

    @Test
    void stream() {
        IntStream.range(0, 1000000)
                .forEach(System.out::println);
    }
}
