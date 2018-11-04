package ca.wbac.callbags.basics.operator;

import java.util.function.Function;
import java.util.function.Predicate;

public final class Operator {
    public static <T> Filter<T> filter(final Predicate<T> predicate) {
        return new Filter<>(predicate);
    }

    public static <I, O> Map<I, O> map(final Function<I, O> transformer) {
        return new Map<>(transformer);
    }

    public static <T> Skip<T> skip(final int max) {
        return new Skip<>(max);
    }
}
