package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.Operator;
import ca.wbac.callbags.core.SinkTalkback;
import ca.wbac.callbags.core.SourceInitiator;
import ca.wbac.callbags.core.SourceTalkback;

import java.util.function.Predicate;

public final class Filter<T> implements Operator<T, T> {

    private final Predicate<T> filter;

    Filter(Predicate<T> filter) {
        this.filter = filter;
    }

    @Override
    public SourceInitiator<T> apply(SourceInitiator<T> inputSink) {
        return outputSink -> inputSink.start(new SinkTalkback<T>() {
            @Override
            public void start(SourceTalkback sourceTalkback) {
                outputSink.start(sourceTalkback);
            }

            @Override
            public void deliver(T data) {
                if (filter.test(data)) {
                    outputSink.deliver(data);
                }
            }
        });
    }
}
