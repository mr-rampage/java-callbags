package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.basics.Callbag;
import ca.wbac.callbags.basics.ISink;
import ca.wbac.callbags.basics.ISource;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

final class FromIterable<T, E> implements ISource<T, E> {

    private final Iterable<T> iterable;

    public FromIterable(final Iterable<T> iterable) {
        this.iterable = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void greet(ISink<T, E> sink) {
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

    @Override
    public void request() {
    }
}
