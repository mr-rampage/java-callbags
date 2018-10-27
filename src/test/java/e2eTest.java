import org.junit.jupiter.api.Test;

import static ca.wbac.callbag.sink.Pullers.forEach;
import static ca.wbac.callbag.source.Pullables.range;

class e2eTest {
    @Test
    void test1() {
        range(0, 10)
            .greet(forEach(System.out::println));
    }
}
