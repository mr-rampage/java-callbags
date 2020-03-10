package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.basics.ISource;

import java.util.concurrent.Flow.Publisher;

public interface Source {
    static ISource<Integer> range(final int lowerBound, final int upperBound) {
        return new Range(lowerBound, upperBound);
    }

    static ISource<Integer> interval(final long period) {
        return new Interval(period);
    }

    static <T> ISource<T> fromIter(final java.lang.Iterable<T> iterable) {
        return new Iterable<>(iterable);
    }

    static <T> ISource<T> fromFlow(final Publisher<T> publisher) {
        return new Flow<>(publisher);
    }
}
