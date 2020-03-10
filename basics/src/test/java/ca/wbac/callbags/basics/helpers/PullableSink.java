package ca.wbac.callbags.basics.helpers;

import ca.wbac.callbags.basics.ISink;
import ca.wbac.callbags.basics.ISource;

import java.util.Collection;

public class PullableSink<I> implements ISink<I> {
    private ISource<I> talkback;
    private Collection<I> processed;

    public PullableSink(Collection<I> processed) {
        this.processed = processed;
    }

    @Override
    public void greet(ISource<I> talkback) {
        this.talkback = talkback;
        this.talkback.request();
    }

    @Override
    public void deliver(I data) {
        processed.add(data);
        this.talkback.request();
    }
}
