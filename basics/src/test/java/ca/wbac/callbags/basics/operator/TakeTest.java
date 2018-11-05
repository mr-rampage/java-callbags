package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.Operator;
import ca.wbac.callbags.core.SourceTalkback;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

final class TakeTest extends AbstractOperatorTest<Integer, Integer> {
    @Test
    @DisplayName("should handshake")
    void testHandshake() {
        ArgumentCaptor<SourceTalkback> sinkTalkbackCaptor = ArgumentCaptor.forClass(SourceTalkback.class);
        verify(sink).start(sinkTalkbackCaptor.capture());

        sinkTalkbackCaptor.getValue().request();
        verify(talkback).request();
    }

    @Test
    @DisplayName("should only deliver 'max' times")
    void testMaxDelivery() {
        sinkCaptor.getValue().deliver(1);
        sinkCaptor.getValue().deliver(1);
        sinkCaptor.getValue().deliver(1);
        sinkCaptor.getValue().deliver(1);
        verify(sink, times(3)).deliver(any(Integer.class));
        verify(sink).terminate();
        verify(talkback).terminate();
    }

    @Test
    @DisplayName("should propagate terminate signal")
    void testTerminate() {
        sinkCaptor.getValue().terminate();
        verify(sink).terminate();
    }

    @Override
    Operator<Integer, Integer> getFixture() {
        return new Take<>(3);
    }
}
