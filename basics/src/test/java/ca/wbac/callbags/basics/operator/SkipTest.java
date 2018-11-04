package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.Operator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SkipTest extends AbstractOperatorTest<Integer, Integer> {
    @Test
    @DisplayName("should not deliver when deliveries less than max")
    void testSkipDelivery() {
        sinkCaptor.getValue().deliver(5);
        verify(sink, never()).deliver(any(Integer.class));
        verify(talkback).request();
    }

    @Test
    @DisplayName("should deliver when deliveries greater than max")
    void testDelivery() {
        sinkCaptor.getValue().deliver(1);
        sinkCaptor.getValue().deliver(2);
        sinkCaptor.getValue().deliver(3);
        sinkCaptor.getValue().deliver(4);
        verify(talkback, times(3)).request();
        verify(sink).deliver(4);
    }

    @Override
    Operator<Integer, Integer> getFixture() {
        return new Skip<>(3);
    }
}
