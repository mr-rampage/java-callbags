package ca.wbac.callbags.basics.sink;

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

import java.util.function.Consumer;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ForEachTest {
    @Spy
    private SourceInitiator<Integer> inputSource;
    @Spy
    private SourceTalkback sourceTalkback;
    @Spy
    private Consumer<Integer> consumer;
    @Captor
    private ArgumentCaptor<SinkTalkback<Integer>> talkbackCaptor;
    @InjectMocks
    private ForEach<Integer> fixture;

    private SinkTalkback<Integer> talkback;

    @BeforeEach
    void captureTalkback() {
        fixture.accept(inputSource);
        verify(inputSource).start(talkbackCaptor.capture());
        talkback = talkbackCaptor.getValue();
    }

    @Test
    @DisplayName("should request data on handshake")
    void testHandshake() {
        talkback.start(sourceTalkback);
        verify(sourceTalkback).request();
    }

    @Test
    @DisplayName("should consumer data and request data")
    void testDelivery() {
        talkback.start(sourceTalkback);
        reset(sourceTalkback);

        talkback.deliver(5);

        verify(consumer).accept(5);
        verify(sourceTalkback).request();
    }
}
