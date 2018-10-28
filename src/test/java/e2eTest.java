import ca.wbac.callbag.Utils;
import org.junit.jupiter.api.Test;

import static ca.wbac.callbag.Utils.pipe;
import static ca.wbac.callbag.operator.Operators.map;
import static ca.wbac.callbag.sink.Pullers.forEach;
import static ca.wbac.callbag.source.Pullables.range;

class e2eTest {
    @Test
    void test1() {
        pipe(
                range(0, 10),
                map((Integer x) -> 2 * x),
                forEach(System.out::println)
        );
    }
}
