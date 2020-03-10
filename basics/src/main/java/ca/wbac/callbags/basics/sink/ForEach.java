package ca.wbac.callbags.basics.sink;

import ca.wbac.callbags.basics.ISink;
import ca.wbac.callbags.basics.ISource;

import java.util.function.Consumer;

final class ForEach<I> implements ISink<I> {
    private Consumer<I> operation;
    private ISource<I> talkback;

    ForEach(Consumer<I> operation) {
        this.operation = operation;
    }

    @Override
    public void greet(ISource<I> talkback) {
        this.talkback = talkback;
        this.talkback.request();
    }

    @Override
    public void deliver(I data) {
        this.operation.accept(data);
        this.talkback.request();
    }
}
