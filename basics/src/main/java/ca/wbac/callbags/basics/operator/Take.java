package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.AbstractOperator;

public final class Take<T> extends AbstractOperator<T, T> {
    private final int max;
    private int taken = 0;

    Take(int max) {
        this.max = max;
    }

    @Override
    public void deliver(T data) {
        if (taken < max) {
            taken++;
            sink.deliver(data);
        } else {
            sink.terminate();
            source.terminate();
        }
    }
}
