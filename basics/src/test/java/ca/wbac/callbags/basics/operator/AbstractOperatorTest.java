package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.Operator;
import ca.wbac.callbags.core.SinkTalkback;
import ca.wbac.callbags.core.SourceInitiator;
import ca.wbac.callbags.core.SourceTalkback;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Spy;

import static org.mockito.Mockito.verify;

abstract class AbstractOperatorTest<I, O> {
    @Spy
    protected SourceInitiator<I> source;
    @Spy
    protected SinkTalkback<O> sink;
    @Spy
    protected SourceTalkback talkback;

    abstract Operator<I, O> getFixture();

    @Captor
    protected ArgumentCaptor<SinkTalkback<I>> sinkCaptor;

    @BeforeEach
    void whenStarted() {
        getFixture().apply(source).start(sink);
        verify(source).start(sinkCaptor.capture());
        sinkCaptor.getValue().start(talkback);
    }

    @Test
    @DisplayName("should handshake")
    void testHandshake() {
        verify(sink).start(talkback);
    }
}
