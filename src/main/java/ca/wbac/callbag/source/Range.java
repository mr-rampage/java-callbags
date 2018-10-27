package ca.wbac.callbag.source;

import ca.wbac.callbag.sink.IPuller;

public class Range implements IPullable<Integer> {
    private final Integer lowerBound;
    private final Integer upperBound;
    private boolean started;
    private IPuller<Integer> talkback;

    public Range(final Integer lowerBound, final Integer upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
    @Override
    public void greet(IPuller<Integer> sink) {
        talkback = sink;
        talkback.greet(this);
    }

    @Override
    public void pull() {
        if (!started) {
            started = true;
            int i = lowerBound;
            while (i < upperBound) {
                talkback.push(i++);
            }
            talkback.goodbye();
        }
    }

    @Override
    public void goodbye() {

    }
}
