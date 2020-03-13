package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.core.Callbag;
import ca.wbac.callbags.core.Sink;
import ca.wbac.callbags.core.Source;

final class Range implements Source<Integer> {
    private final Integer upperBound;
    private final Integer lowerBound;

    Range(final Integer lowerBound, final Integer upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public void greet(Sink<Integer> sink) {
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
}
