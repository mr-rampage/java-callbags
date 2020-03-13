package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.basics.AbstractOperator;

public final class Skip<T> extends AbstractOperator<T, T> {
    private final int max;
    private int skipped = 0;

    Skip(int max) {
        this.max = max;
    }

    @Override
    public void deliver(T data) {
        if (skipped < max) {
            skipped++;
            source.request();
        } else {
            sink.deliver(data);
        }
    }
}
