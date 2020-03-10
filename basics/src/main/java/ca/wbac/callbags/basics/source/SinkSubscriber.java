package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.basics.ISink;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

final class SinkSubscriber<T, E> implements Subscriber<T> {
    private Subscription subscription;
    private ISink<T, E> sink;

    SinkSubscriber(final ISink<T, E> sink) {
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
        this.sink.terminate();
    }

    @Override
    public void onComplete() {
        this.sink.terminate();
    }
}
