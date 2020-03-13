package ca.wbac.callbags.basics.helpers;

import ca.wbac.callbags.core.Callbag;
import ca.wbac.callbags.core.Sink;
import ca.wbac.callbags.core.Source;

import java.util.List;

public final class PullableSource<T> implements Source<T> {
    private int sent = 0;
    private final List<T> list;

    public PullableSource(List<T> list) {
        this.list = list;
    }

    @Override
    public void greet(Sink<T> sink) {
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
