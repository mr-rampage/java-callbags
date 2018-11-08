package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.Operator;
import ca.wbac.callbags.core.SinkTalkback;
import ca.wbac.callbags.core.SourceInitiator;
import ca.wbac.callbags.core.SourceTalkback;

public final class Skip<T> implements Operator<T, T> {
    private final int max;

    Skip(int max) {
        this.max = max;
    }

    @Override
    public SourceInitiator<T> apply(SourceInitiator<T> inputSink) {
        return outputSink -> inputSink.start(new SinkTalkback<T>() {
            private int skipped = 0;
            private SourceTalkback source;

            @Override
            public void start(SourceTalkback sourceTalkback) {
                source = sourceTalkback;
                outputSink.start(sourceTalkback);
            }

            @Override
            public void deliver(T data) {
                if (skipped < max) {
                    skipped++;
                    source.request();
                } else {
                    outputSink.deliver(data);
                }
            }
        });
    }
}
