package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.Operator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import java.util.function.Function;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

final class MapTest extends AbstractOperatorTest<Integer, String> {
    @Spy
    private Function<Integer, String> transformer;
    @InjectMocks
    private Map<Integer, String> fixture;

    @Test
    @DisplayName("should transform data for delivery")
    void testTransformedDelivery() {
        when(transformer.apply(5)).thenReturn("Hello World!");
        sinkCaptor.getValue().deliver(5);
        verify(sink).deliver("Hello World!");
    }

    @Override
    Operator<Integer, String> getFixture() {
        return fixture;
    }
}
