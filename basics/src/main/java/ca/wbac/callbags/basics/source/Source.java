package ca.wbac.callbags.basics.source;

import java.util.concurrent.Flow.Publisher;

public interface Source {
    static ca.wbac.callbags.core.Source<Integer> range(final int lowerBound, final int upperBound) {
        return new Range(lowerBound, upperBound);
    }

    static ca.wbac.callbags.core.Source<Integer> interval(final long period) {
        return new Interval(period);
    }

    static <T> ca.wbac.callbags.core.Source<T> fromIter(final java.lang.Iterable<T> iterable) {
        return new Iterable<>(iterable);
    }

    static <T> ca.wbac.callbags.core.Source<T> fromFlow(final Publisher<T> publisher) {
        return new Flow<>(publisher);
    }
}
