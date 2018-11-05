package ca.wbac.callbags.basics.operator;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Operator {
    public static <T> Filter<T> filter(final Predicate<T> predicate) {
        return new Filter<>(predicate);
    }

    public static <I, O> Map<I, O> map(final Function<I, O> transformer) {
        return new Map<>(transformer);
    }

    public static <I, O> Scan<I, O> scan(final BiFunction<O, I, O> reducer, O seed) {
        return new Scan<>(reducer, seed);
    }

    public static <T> Skip<T> skip(final int max) {
        return new Skip<>(max);
    }

    public static <T> Take<T> take(final int max) {
        return new Take<>(max);
    }
}
