package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.core.SinkTalkback;
import ca.wbac.callbags.core.SourceInitiator;
import ca.wbac.callbags.core.SourceTalkback;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RangeTest {
    private SinkTalkback<Integer> sink = spy(new SinkTalkback<>() {
        @Override
        public void start(SourceTalkback sourceTalkback) {
        }

        @Override
        public void deliver(Integer data) {
        }
    });
    private ArgumentCaptor<SourceTalkback> talkbackCaptor = ArgumentCaptor.forClass(SourceTalkback.class);
    private SourceInitiator<Integer> fixture = new Range(0, 10).get();

    @BeforeEach
    void whenStarted() {
        fixture.start(sink);
        verify(sink).start(talkbackCaptor.capture());
        talkbackCaptor.getValue().request();
    }

    @Test
    @DisplayName("should delivery all whole numbers between a range exclusively")
    void testDelivery() {
        ArgumentCaptor<Integer> deliveryCalls = ArgumentCaptor.forClass(Integer.class);
        verify(sink, times(10)).deliver(deliveryCalls.capture());

        assertThat(deliveryCalls.getAllValues(), is(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    @Test
    @DisplayName("should terminate the sink after all values are delivered")
    void testTermination() {
        verify(sink).terminate();
    }

    @Test
    @DisplayName("should drop multiple requests")
    void testMultipleRequests() {
        talkbackCaptor.getValue().request();
        talkbackCaptor.getValue().request();
        talkbackCaptor.getValue().request();
        verify(sink, times(10)).deliver(any(Integer.class));
    }
}
