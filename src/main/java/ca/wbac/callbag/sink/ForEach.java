package ca.wbac.callbag.sink;

import ca.wbac.callbag.Callbag;

import java.util.function.Consumer;

final class ForEach<T> extends Callbag<T> {
    private final Consumer<T> consumer;
    private Callbag inputSource;

    ForEach(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void greet(Callbag source) {
        inputSource = source;
        inputSource.data();
    }

    @Override
    public void data(T response) {
        consumer.accept(response);
        inputSource.data();
    }
}
