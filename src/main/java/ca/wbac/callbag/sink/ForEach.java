package ca.wbac.callbag.sink;

import ca.wbac.callbag.ICallbag;
import ca.wbac.callbag.IPuller;
import ca.wbac.callbag.IPullable;

import java.util.function.Consumer;

public class ForEach<T> implements IPuller<T> {
    private final Consumer<T> consumer;
    private IPullable<T> talkback;

    public ForEach(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void greet(ICallbag<T> source) {
        talkback = (IPullable<T>)source;
        talkback.pull();
    }

    @Override
    public void push(T response) {
        consumer.accept(response);
        talkback.pull();
    }

    @Override
    public void goodbye() {

    }
}
