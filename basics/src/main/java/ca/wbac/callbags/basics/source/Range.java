package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.core.Source;
import ca.wbac.callbags.core.SourceFactory;
import ca.wbac.callbags.core.SourceTalkback;

public final class Range implements SourceFactory<Integer> {
    private final Integer lowerBound;
    private final Integer upperBound;

    Range(final Integer lowerBound, final Integer upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public Source<Integer> get() {
        return sinkTalkback -> sinkTalkback.start(new SourceTalkback() {
            private boolean started = false;

            @Override
            public void request() {
                if (!started) {
                    started = true;
                    Integer i = lowerBound;
                    while (i < upperBound) {
                        sinkTalkback.deliver(i++);
                    }
                    sinkTalkback.terminate();
                }
            }
        });
    }
}
