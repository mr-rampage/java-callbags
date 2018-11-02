package ca.wbac.callbags.basics.sink;

import ca.wbac.callbags.core.SinkTalkback;
import ca.wbac.callbags.core.SourceTalkback;

import java.util.function.Consumer;

final class ForEach<T> implements SinkTalkback<T> {
    private final Consumer<T> consumer;
    private SourceTalkback sourceTalkback;

    ForEach(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void start(SourceTalkback sourceTalkback) {
        this.sourceTalkback = sourceTalkback;
        sourceTalkback.request();
    }

    @Override
    public void deliver(T data) {
        consumer.accept(data);
        this.sourceTalkback.request();
    }
}
