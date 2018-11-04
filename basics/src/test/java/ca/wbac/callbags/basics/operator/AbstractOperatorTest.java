package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.SinkTalkback;
import ca.wbac.callbags.core.SourceInitiator;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Spy;

abstract class AbstractOperatorTest {
    @Spy
    protected SourceInitiator<Integer> source;
    @Spy
    protected SinkTalkback<Integer> sink;
    @Captor
    protected ArgumentCaptor<SinkTalkback<Integer>> sinkCaptor;
}
