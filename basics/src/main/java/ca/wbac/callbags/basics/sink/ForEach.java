package ca.wbac.callbags.basics.sink;

import ca.wbac.callbags.core.SinkTalkback;
import ca.wbac.callbags.core.SinkTerminator;
import ca.wbac.callbags.core.SourceInitiator;
import ca.wbac.callbags.core.SourceTalkback;

import java.util.function.Consumer;

public final class ForEach<T> implements SinkTerminator<T> {
    private final Consumer<T> consumer;

    ForEach(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void accept(SourceInitiator<T> inputSource) {
        inputSource.start(new SinkTalkback<T>() {
            private SourceTalkback sourceTalkback;

            @Override
            public void start(SourceTalkback sourceTalkback) {
                this.sourceTalkback = sourceTalkback;
                sourceTalkback.request();
            }

            @Override
            public void deliver(T data) {
                consumer.accept(data);
                sourceTalkback.request();
            }
        });
    }
}
