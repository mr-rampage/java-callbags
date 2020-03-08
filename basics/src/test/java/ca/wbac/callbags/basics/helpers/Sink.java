package ca.wbac.callbags.basics.helpers;

import ca.wbac.callbags.basics.ISink;
import ca.wbac.callbags.basics.ISource;

import java.util.concurrent.CompletableFuture;

public class Sink<E> implements ISink<Integer, E> {
    private final CompletableFuture<Integer> future;
    private ISource<Integer, E> talkback;
    private int expectedMessages;
    private int receivedMessages = 0;

    public Sink(int expectedMessages, CompletableFuture<Integer> future) {
        this.expectedMessages = expectedMessages;
        this.future = future;
    }

    @Override
    public void greet(ISource<Integer, E> talkback) {
        this.talkback = talkback;
        this.talkback.request();
    }

    @Override
    public void deliver(Integer data) {
        this.receivedMessages++;
        if (this.expectedMessages == this.receivedMessages) {
            this.talkback.terminate();
            future.complete(data);
        }
        this.talkback.request();
    }
}
