package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.basics.AbstractOperator;

import java.util.function.Predicate;

final class Filter<T> extends AbstractOperator<T, T> {
    private final Predicate<T> filter;

    Filter(Predicate<T> filter) {
        this.filter = filter;
    }

    @Override
    public void deliver(T data) {
        if (filter.test(data)) {
            this.sink.deliver(data);
        } else {
            this.source.request();
        }
    }
}
