package ca.wbac.callbag.sink;

import ca.wbac.callbag.Callbag;

import java.util.function.Consumer;

final class ForEach extends Callbag {
    private final Consumer consumer;
    private Callbag inputSource;

    ForEach(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void greet(Callbag source) {
        inputSource = source;
        inputSource.data();
    }

    @Override
    public void data(Object response) {
        consumer.accept(response);
        inputSource.data();
    }
}
