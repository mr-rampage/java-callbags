package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.basics.Callbag;
import ca.wbac.callbags.basics.ISink;
import ca.wbac.callbags.basics.ISource;

import java.util.concurrent.Flow.Publisher;

final class Flow<T, E> implements ISource<T, E> {

    private final Publisher<T> publisher;

    Flow(final Publisher<T> publisher) {
        this.publisher = publisher;
    }

    @Override
    public void greet(final ISink<T, E> sink) {
        final SinkSubscriber<T, E> subscriber = new SinkSubscriber<>(sink);
        this.publisher.subscribe(subscriber);

        sink.greet(new Callbag<>() {
            @Override
            public void terminate() {
                subscriber.dispose();
            }
        });
    }

    @Override
    public void request() {

    }
}
