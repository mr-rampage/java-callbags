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

import java.util.function.Function;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MapTest {
    @Spy
    private SourceInitiator<Integer> source;
    @Spy
    private SinkTalkback<String> sink;
    @Captor
    private ArgumentCaptor<SinkTalkback<Integer>> sinkCaptor;
    @Spy
    private Function<Integer, String> transformer;
    @InjectMocks
    private Map<Integer, String> fixture;

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
    @DisplayName("should transform data for delivery")
    void testTransformedDelivery() {
        when(transformer.apply(5)).thenReturn("Hello World!");
        sinkCaptor.getValue().deliver(5);
        verify(sink).deliver("Hello World!");
    }

}
