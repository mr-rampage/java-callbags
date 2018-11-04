package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.SinkTalkback;
import ca.wbac.callbags.core.SourceInitiator;
import ca.wbac.callbags.core.SourceTalkback;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Predicate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilterTest {
    @Spy
    private SourceInitiator<Integer> source;
    @Spy
    private SinkTalkback<Integer> sink;
    @Captor
    private ArgumentCaptor<SinkTalkback<Integer>> sinkCaptor;
    @Spy
    private Predicate<Integer> predicate;
    @InjectMocks
    private Filter<Integer> fixture;

    @BeforeEach
    void whenStarted() {
        fixture.apply(source).start(sink);
        verify(source).start(sinkCaptor.capture());
    }

    @Test
    @DisplayName("should handshake")
    void testHandshake() {
        SourceTalkback talkback = spy(SourceTalkback.class);
        sinkCaptor.getValue().start(talkback);
        verify(sink).start(talkback);
    }

    @Test
    @DisplayName("should not deliver data when predicate is false")
    void testFalsyPredicate() {
        sinkCaptor.getValue().deliver(5);
        verify(sink, never()).deliver(any(Integer.class));
    }

    @Test
    @DisplayName("should deliver data when predicate is true")
    void testTruthyPredicate() {
        when(predicate.test(5)).thenReturn(true);
        sinkCaptor.getValue().deliver(5);
        verify(sink).deliver(5);
    }
}
