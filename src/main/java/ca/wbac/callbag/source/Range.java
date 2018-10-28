package ca.wbac.callbag.source;

import ca.wbac.callbag.Callbag;

final class Range extends Callbag {
    private final Integer lowerBound;
    private final Integer upperBound;

    Range(final Integer lowerBound, final Integer upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public void greet(Callbag sink) {
        Callbag talkback = new Callbag() {
            private boolean started = false;

            @Override
            public void data() {
                if (!started) {
                    started = true;
                    int i = lowerBound;
                    while (i < upperBound) {
                        sink.data(i++);
                    }
                    sink.terminate();
                }
            }
        };
        sink.greet(talkback);
    }
}
