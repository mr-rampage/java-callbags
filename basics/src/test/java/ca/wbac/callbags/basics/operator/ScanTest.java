package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.Operator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;

final class ScanTest extends AbstractOperatorTest<Integer, Integer> {

    @Test
    @DisplayName("should deliver accumulated value")
    void testDeliver() {
        sinkCaptor.getValue().deliver(1);
        verify(sink).deliver(1);

        sinkCaptor.getValue().deliver(2);
        verify(sink).deliver(3);

        sinkCaptor.getValue().deliver(9);
        verify(sink).deliver(12);
    }

    @Override
    Operator<Integer, Integer> getFixture() {
        return new Scan<>((acc, value) -> acc + value, 0);
    }
}
