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
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Consumer;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ForEachTest {
    @Spy
    private SourceInitiator<Integer> inputSource;
    @Spy
    private SourceTalkback sourceTalkback;
    private Consumer<Integer> consumer = spy(new Consumer<>() {
        @Override
        public void accept(Integer integer) {

        }
    });
    @Captor
    private ArgumentCaptor<SinkTalkback<Integer>> talkbackCaptor;

    private SinkTalkback<Integer> fixture;

    @BeforeEach
    void captureTalkback() {
        ForEach<Integer> forEach = new ForEach<>(consumer);
        forEach.accept(inputSource);
        verify(inputSource).start(talkbackCaptor.capture());
        fixture = talkbackCaptor.getValue();
    }

    @Test
    @DisplayName("should request data on handshake")
    void testHandshake() {
        fixture.start(sourceTalkback);
        verify(sourceTalkback).request();
    }

    @Test
    @DisplayName("should consumer data and request data")
    void testDelivery() {
        fixture.start(sourceTalkback);
        reset(sourceTalkback);

        fixture.deliver(5);

        verify(consumer).accept(5);
        verify(sourceTalkback).request();
    }
}
