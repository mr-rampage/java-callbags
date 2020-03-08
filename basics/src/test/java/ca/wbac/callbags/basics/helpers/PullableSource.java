package ca.wbac.callbags.basics.helpers;

import ca.wbac.callbags.basics.ISink;
import ca.wbac.callbags.basics.ISource;

import java.util.List;

public final class PullableSource<T, E> implements ISource<T, E> {
    private ISink<T, E> sink;
    private int sent = 0;
    private final List<T> list;

    private PullableSource(List<T> list) {
        this.list = list;
    }

    public static <T, E> PullableSource<T, E> of(List<T> list) {
        return new PullableSource<>(list);
    }

    @Override
    public void greet(ISink<T, E> sink) {
        this.sink = sink;
        sink.greet(this);
    }

    @Override
    public void request() {
        if (sent == list.size()) {
            this.sink.terminate();
        } else {
            this.sink.deliver(list.get(sent++));
        }
    }
}
