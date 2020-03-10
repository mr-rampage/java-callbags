package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.basics.ISink;

import java.util.concurrent.Flow.Publisher;
import java.util.function.Consumer;

public interface Source {
    static Consumer<ISink<Integer>> range(final int lowerBound, final int upperBound) {
        return sink -> new Range(lowerBound, upperBound).greet(sink);
    }

    static Consumer<ISink<Integer>> interval(final long period) {
        return sink -> new Interval(period).greet(sink);
    }

    static <T> Consumer<ISink<T>> fromIter(final java.lang.Iterable<T> iterable) {
        return sink -> new Iterable<>(iterable).greet(sink);
    }

    static <T> Consumer<ISink<T>> fromFlow(final Publisher<T> publisher) {
        return sink -> new Flow<>(publisher).greet(sink);
    }
}
