package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.core.Callbag;
import ca.wbac.callbags.core.Sink;
import ca.wbac.callbags.core.Source;

import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

final class Flow<T> implements Source<T> {

    private final Publisher<T> publisher;

    Flow(final Publisher<T> publisher) {
        this.publisher = publisher;
    }

    @Override
    public void greet(final Sink<T> sink) {
        final SinkSubscriber<T> subscriber = new SinkSubscriber<>(sink);
        this.publisher.subscribe(subscriber);

        sink.greet(new Callbag<>() {
            @Override
            public void terminate() {
                subscriber.dispose();
            }
        });
    }

    private final static class SinkSubscriber<T> implements Subscriber<T> {
        private Subscription subscription;
        private Sink<T> sink;

        SinkSubscriber(final Sink<T> sink) {
            this.sink = sink;
        }

        @Override
        public void onSubscribe(final Subscription subscription) {
            this.subscription = subscription;
            this.subscription.request(Long.MAX_VALUE);
        }

        public void dispose() {
            if (this.subscription != null) {
                this.subscription.cancel();
            }
        }

        @Override
        public void onNext(final T item) {
            this.sink.deliver(item);
        }

        @Override
        public void onError(final Throwable throwable) {
            this.sink.terminate(throwable);
        }

        @Override
        public void onComplete() {
            this.sink.terminate();
        }
    }
}
