package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.AbstractOperator;

import java.util.function.BiFunction;

public final class Scan<I, O> extends AbstractOperator<I, O> {

    private final BiFunction<O, I, O> reducer;
    private O accumulator;

    Scan(BiFunction<O, I, O> reducer, O seed) {
        this.reducer = reducer;
        this.accumulator = seed;
    }

    @Override
    public void deliver(I data) {
        accumulator = reducer.apply(accumulator, data);
        sink.deliver(accumulator);
    }
}
