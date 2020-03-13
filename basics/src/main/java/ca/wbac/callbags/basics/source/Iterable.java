package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.basics.Callbag;
import ca.wbac.callbags.basics.ISink;
import ca.wbac.callbags.basics.ISource;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

final class Iterable<T> implements ISource<T> {

    private final java.lang.Iterable<T> iterable;

    public Iterable(final java.lang.Iterable<T> iterable) {
        this.iterable = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void greet(ISink<T> sink) {
        sink.greet(new Callbag<>() {
            Iterator<T> iterator = iterable.iterator();

            @Override
            public void request() {
                if (iterator.hasNext()) {
                    sink.deliver(iterator.next());
                }
            }
        });
    }
}
