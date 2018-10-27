package ca.wbac.callbag.sink;

import ca.wbac.callbag.source.IPullable;

import java.util.function.Consumer;

public class ForEach implements IPuller<Integer> {
    private IPullable<Integer> talkback;
    Consumer<Integer> onData;

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
