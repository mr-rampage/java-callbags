package ca.wbac.callbags.basics.sink;

import ca.wbac.callbags.core.SourceTalkback;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Consumer;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ForEachTest {
    @Test
    @DisplayName("should start request upon handshake")
    void testHandshake() {
        SourceTalkback spiedSource = spy(SourceTalkback.class);
        doNothing().when(spiedSource).request();
        new ForEach<Integer>(x -> {
        })
                .accept(sinkTalkback -> sinkTalkback.start(spiedSource));
        verify(spiedSource).request();
    }

    @Test
    @DisplayName("should start request upon handshake")
    void testDelivery() {
        Consumer<Integer> consumer = spy(i -> {
        });
        new ForEach<>(consumer)
                .accept(sinkTalkback -> sinkTalkback.start(new SourceTalkback() {
                    boolean started = false;

                    @Override
                    public void request() {
                        if (!started) {
                            started = true;
                            sinkTalkback.deliver(5);
                        }
                    }
                }));
        verify(consumer).accept(5);
    }
}
