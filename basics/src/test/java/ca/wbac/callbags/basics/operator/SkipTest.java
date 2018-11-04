package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.SourceTalkback;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SkipTest extends AbstractOperatorTest {
    @Spy
    private SourceTalkback talkback;

    private Skip<Integer> fixture = new Skip<>(3);

    @BeforeEach
    void whenStarted() {
        fixture.apply(source).start(sink);
        verify(source).start(sinkCaptor.capture());
        sinkCaptor.getValue().start(talkback);
    }

    @Test
    @DisplayName("should handshake")
    void testHandshake() {
        verify(sink).start(talkback);
    }

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
}
