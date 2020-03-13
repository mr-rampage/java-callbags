package ca.wbac.callbags.basics.helpers;

import ca.wbac.callbags.core.Sink;
import ca.wbac.callbags.core.Source;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PushableSink<I> implements Sink<I> {
    private final CompletableFuture<Collection<I>> future;
    private Source<I> talkback;
    private List<I> processed;
    private int receivedMessages = 0;

    @SuppressWarnings("unchecked")
    public PushableSink(int messagesToProcess, CompletableFuture<Collection<I>> future) {
        this.processed = Arrays.asList((I[]) new Object[messagesToProcess]);
        this.future = future;
    }

    @Override
    public void greet(Source<I> talkback) {
        this.talkback = talkback;
        this.talkback.request();
    }

    @Override
    public void deliver(I data) {
        this.processed.set(this.receivedMessages++, data);
        if (this.processed.size() == this.receivedMessages) {
            future.complete(this.processed);
            this.talkback.terminate();
        } else {
            this.talkback.request();
        }
    }

    @Override
    public void terminate() {
        future.complete(this.processed);
    }
}
