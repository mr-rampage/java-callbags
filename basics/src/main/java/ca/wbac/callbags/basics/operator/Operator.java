package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.basics.AbstractOperator;

import java.util.function.BiFunction;
import java.util.function.Function;

public final class Operator {
    public static <I> AbstractOperator<I, I> identity() {
        return new Identity<>();
    }

    public static <I, O> AbstractOperator<I, O> map(final Function<I, O> transformer) {
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
