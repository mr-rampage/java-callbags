package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.basics.Callbag;
import ca.wbac.callbags.basics.ISink;
import ca.wbac.callbags.basics.ISource;

final class Range<E> implements ISource<Integer, E> {
    private final Integer upperBound;
    private final Integer lowerBound;

    Range(final Integer lowerBound, final Integer upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public void greet(ISink<Integer, E> sink) {
        sink.greet(new Callbag<>() {
            private Integer currentValue = lowerBound;

            @Override
            public void request() {
                if (currentValue <= upperBound) {
                    sink.deliver(currentValue++);
                }
            }
        });
    }

    @Override
    public void request() {
    }
}
