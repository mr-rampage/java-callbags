package ca.wbac.callbag.source;

import ca.wbac.callbag.ICallbag;
import ca.wbac.callbag.IPullable;
import ca.wbac.callbag.IPuller;

public final class Range implements IPullable<Integer> {
    private final Integer lowerBound;
    private final Integer upperBound;
    private boolean started;
    private IPuller<Integer> talkback;

    Range(final Integer lowerBound, final Integer upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public void greet(ICallbag<Integer> sink) {
        talkback = (IPuller<Integer>)sink;
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
