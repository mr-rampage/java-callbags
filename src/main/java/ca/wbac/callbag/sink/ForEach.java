package ca.wbac.callbag.sink;

import ca.wbac.callbag.IPuller;
import ca.wbac.callbag.IPullable;

import java.util.function.Consumer;

public class ForEach<T> implements IPuller<T> {
    private final Consumer<T> onData;
    private IPullable<T> talkback;

    public ForEach(Consumer<T> onData) {
        this.onData = onData;
    }

    @Override
    public void greet(IPullable<T> source) {
        talkback = source;
        talkback.pull();
    }

    @Override
    public void push(T response) {
        onData.accept(response);
        talkback.pull();
    }

    @Override
    public void goodbye() {

    }
}
