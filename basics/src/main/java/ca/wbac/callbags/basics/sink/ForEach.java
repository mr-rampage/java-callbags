package ca.wbac.callbags.basics.sink;

import ca.wbac.callbags.core.Sink;
import ca.wbac.callbags.core.Source;

import java.util.function.Consumer;

final class ForEach<I> implements Sink<I> {
    private Consumer<I> operation;
    private Source<I> talkback;

    ForEach(Consumer<I> operation) {
        this.operation = operation;
    }

    @Override
    public void greet(Source<I> talkback) {
        this.talkback = talkback;
        this.talkback.request();
    }

    @Override
    public void deliver(I data) {
        this.operation.accept(data);
        this.talkback.request();
    }
}
