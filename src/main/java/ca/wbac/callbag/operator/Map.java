package ca.wbac.callbag.operator;

import ca.wbac.callbag.Callbag;

import java.util.function.Function;

final class Map<I, O> extends Callbag<I> {
    private final Function<I, O> transform;
    private Callbag inputSource;

    Map(Function<I, O> transform, Callbag inputSource) {
        this.transform = transform;
        this.inputSource = inputSource;
    }

    @Override
    public void greet(Callbag outputSink) {
        final Callbag<I> talkback = new Callbag<>() {
            @Override
            public void greet(Callbag callbag) {
                outputSink.greet(callbag);
            }

            @Override
            public void data() {
                outputSink.data();
            }

            @Override
            public void data(I response) {
                outputSink.data(transform.apply(response));
            }

            @Override
            public void terminate() {
                outputSink.terminate();
            }
        };
        inputSource.greet(talkback);
    }
}
