package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.AbstractOperator;

import java.util.function.Function;

public final class Map<I, O> extends AbstractOperator<I, O> {
    private Function<I, O> transform;

    Map(Function<I, O> transform) {
        this.transform = transform;
    }

    @Override
    public void deliver(I data) {
        this.sink.deliver(this.transform.apply(data));
    }
}
