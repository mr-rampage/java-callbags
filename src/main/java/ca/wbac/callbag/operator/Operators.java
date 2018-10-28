package ca.wbac.callbag.operator;

import ca.wbac.callbag.Pipeable;

import java.util.function.Function;

public final class Operators {
    public static <I, O> Pipeable map(Function<I, O> transformer) {
        return inputSource -> new Map<>(transformer, inputSource);
    }
}
