package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.basics.ISink;

import java.util.function.Consumer;

public final class Source {
    public static <E> Consumer<ISink<Integer, E>> range(final int lowerBound, final int upperBound) {
        return sink -> new Range<E>(lowerBound, upperBound).greet(sink);
    }

    public static <E> Consumer<ISink<Integer, E>> interval(final long period) {
        return sink -> new Interval<E>(period).greet(sink);
    }
}
