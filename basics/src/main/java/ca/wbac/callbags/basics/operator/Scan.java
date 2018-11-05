package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.Operator;
import ca.wbac.callbags.core.SinkTalkback;
import ca.wbac.callbags.core.SourceInitiator;
import ca.wbac.callbags.core.SourceTalkback;

import java.util.function.BiFunction;

public final class Scan<I, O> implements Operator<I, O> {

    private final BiFunction<O, I, O> reducer;
    private final O seed;

    Scan(BiFunction<O, I, O> reducer, O seed) {
        this.reducer = reducer;
        this.seed = seed;
    }

    @Override
    public SourceInitiator<O> apply(SourceInitiator<I> inputSink) {
        return outputSink -> inputSink.start(new SinkTalkback<>() {
            private O accumulator = seed;

            @Override
            public void start(SourceTalkback sourceTalkback) {
                outputSink.start(sourceTalkback);
            }

            @Override
            public void deliver(I data) {
                accumulator = reducer.apply(accumulator, data);
                outputSink.deliver(accumulator);
            }
        });
    }
}
