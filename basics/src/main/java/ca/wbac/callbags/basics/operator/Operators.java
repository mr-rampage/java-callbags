package ca.wbac.callbags.basics.operator;

import java.util.function.Function;

public class Operators {
    public static <I, O> Map<I, O> map(Function<I, O> transformer) {
        return new Map<>(transformer);
    }
}
