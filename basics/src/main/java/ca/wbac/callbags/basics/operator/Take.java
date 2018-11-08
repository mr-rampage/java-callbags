package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.Operator;
import ca.wbac.callbags.core.SinkTalkback;
import ca.wbac.callbags.core.Source;
import ca.wbac.callbags.core.SourceTalkback;

public final class Take<T> implements Operator<T, T> {
    private final int max;

    Take(int max) {
        this.max = max;
    }

    @Override
    public Source<T> apply(Source<T> inputSink) {
        return outputSink -> inputSink.start(new SinkTalkback<T>() {
            private int taken = 0;
            private SourceTalkback source;

            @Override
            public void start(SourceTalkback sourceTalkback) {
                source = sourceTalkback;
                outputSink.start(() -> {
                    if (taken < max) {
                        sourceTalkback.request();
                    }
                });
            }

            @Override
            public void deliver(T data) {
                if (taken < max) {
                    taken++;
                    outputSink.deliver(data);
                } else {
                    outputSink.terminate();
                    source.terminate();
                }
            }

            @Override
            public void terminate() {
                outputSink.terminate();
            }
        });
    }
}
