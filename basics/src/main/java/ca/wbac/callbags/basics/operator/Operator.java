package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.basics.AbstractOperator;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Operator {
    public static <I> AbstractOperator<I, I> identity() {
        return new Identity<>();
    }

    public static <I, O> AbstractOperator<I, O> map(final Function<I, O> transformer) {
        return new Map<>(transformer);
    }

    public static <I> AbstractOperator<I, I> filter(final Predicate<I> predicate) {
        return new Filter<>(predicate);
    }

    public static <I, O> AbstractOperator<I, O> scan(final BiFunction<O, I, O> reducer, O seed) {
        return new Scan<>(reducer, seed);
    }

    public static <I> AbstractOperator<I, I> skip(final int max) {
        return new Skip<>(max);
    }

    public static <I> AbstractOperator<I, I> take(final int max) {
        return new Take<>(max);
    }
}
