package ca.wbac.callbags.basics.helpers;

import ca.wbac.callbags.basics.ISink;
import ca.wbac.callbags.basics.ISource;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PushableSink<I, E> implements ISink<I, E> {
    private final CompletableFuture<Collection<I>> future;
    private ISource<I, E> talkback;
    private List<I> processed;
    private int receivedMessages = 0;

    @SuppressWarnings("unchecked")
    public PushableSink(int messagesToProcess, CompletableFuture<Collection<I>> future) {
        this.processed = Arrays.asList((I[]) new Object[messagesToProcess]);
        this.future = future;
    }

    @Override
    public void greet(ISource<I, E> talkback) {
        this.talkback = talkback;
        this.talkback.request();
    }

    @Override
    public void deliver(I data) {
        this.processed.set(this.receivedMessages++, data);
        if (this.processed.size() == this.receivedMessages) {
            this.talkback.terminate();
            future.complete(this.processed);
        }
        this.talkback.request();
    }
}
