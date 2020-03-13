package ca.wbac.callbags.basics.helpers;

import ca.wbac.callbags.core.Sink;
import ca.wbac.callbags.core.Source;

import java.util.Collection;

public class PullableSink<I> implements Sink<I> {
    private Source<I> talkback;
    private Collection<I> processed;

    public PullableSink(Collection<I> processed) {
        this.processed = processed;
    }

    @Override
    public void greet(Source<I> talkback) {
        this.talkback = talkback;
        this.talkback.request();
    }

    @Override
    public void deliver(I data) {
        processed.add(data);
        this.talkback.request();
    }
}
