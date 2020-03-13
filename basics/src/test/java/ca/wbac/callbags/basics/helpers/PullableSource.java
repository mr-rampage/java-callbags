package ca.wbac.callbags.basics.helpers;

import ca.wbac.callbags.basics.Callbag;
import ca.wbac.callbags.basics.ISink;
import ca.wbac.callbags.basics.ISource;

import java.util.List;

public final class PullableSource<T> implements ISource<T> {
    private int sent = 0;
    private final List<T> list;

    public PullableSource(List<T> list) {
        this.list = list;
    }

    @Override
    public void greet(ISink<T> sink) {
        sink.greet(new Callbag<>() {
            @Override
            public void request() {
                if (sent == list.size()) {
                    sink.terminate();
                } else {
                    sink.deliver(list.get(sent++));
                }
            }
        });
    }
}
