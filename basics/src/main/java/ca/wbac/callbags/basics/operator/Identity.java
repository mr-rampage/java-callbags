package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.AbstractOperator;

final class Identity<I> extends AbstractOperator<I, I> {
    @Override
    public void deliver(I data) {
        sink.deliver(data);
    }
}
