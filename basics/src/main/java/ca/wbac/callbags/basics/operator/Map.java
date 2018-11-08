package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.Operator;
import ca.wbac.callbags.core.SinkTalkback;
import ca.wbac.callbags.core.SourceInitiator;
import ca.wbac.callbags.core.SourceTalkback;

import java.util.function.Function;

public final class Map<I, O> implements Operator<I, O> {
    private Function<I, O> transform;

    Map(Function<I, O> transform) {
        this.transform = transform;
    }

    @Override
    public SourceInitiator<O> apply(SourceInitiator<I> inputSink) {
        return outputSink -> inputSink.start(new SinkTalkback<I>() {
            @Override
            public void start(SourceTalkback sourceTalkback) {
                outputSink.start(sourceTalkback);
            }

            @Override
            public void deliver(I data) {
                outputSink.deliver(transform.apply(data));
            }
        });
    }
}
