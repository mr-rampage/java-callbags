package ca.wbac.callbag.sink;

import ca.wbac.callbag.IPuller;
import ca.wbac.callbag.IPullable;

import java.util.function.Consumer;

public class ForEach implements IPuller<Integer> {
    private final Consumer<Integer> onData;
    private IPullable<Integer> talkback;

    public ForEach(Consumer<Integer> onData) {
        this.onData = onData;
    }

    @Override
    public void greet(IPullable<Integer> source) {
        talkback = source;
        talkback.pull();
    }

    @Override
    public void push(Integer response) {
        onData.accept(response);
        talkback.pull();
    }

    @Override
    public void goodbye() {

    }
}
