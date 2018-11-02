package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.core.SinkTalkback;
import ca.wbac.callbags.core.SourceInitiator;
import ca.wbac.callbags.core.SourceTalkback;

final class Range implements SourceInitiator<Integer> {
    private final Integer lowerBound;
    private final Integer upperBound;

    Range(Integer lowerBound, Integer upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public void start(SinkTalkback<Integer> sinkTalkback) {
        SourceTalkback sourceTalkback = new SourceTalkback() {
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
        };
        sinkTalkback.start(sourceTalkback);
    }
}
