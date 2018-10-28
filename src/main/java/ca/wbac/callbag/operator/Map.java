package ca.wbac.callbag.operator;

import ca.wbac.callbag.Callbag;

import java.util.function.Function;

final class Map extends Callbag {
    private final Function transform;
    private Callbag inputSource;

    Map(Function transform, Callbag inputSource) {
        this.transform = transform;
        this.inputSource = inputSource;
    }

    @Override
    public void greet(Callbag outputSink) {
        final Callbag talkback = new Callbag() {
            @Override
            public void greet(Callbag callbag) {
                outputSink.greet(callbag);
            }

            @Override
            public void data() {
                outputSink.data();
            }

            @Override
            public void data(Object response) {
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
