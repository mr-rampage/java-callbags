package ca.wbac.callbags.core;

public interface Callbag<I, O> extends Sink<I>, Source<O> {
    @Override
    default void greet(Source<I> talkback) {

    }

    @Override
    default void deliver(I data) {

    }

    @Override
    default void greet(Sink<O> sink) {

    }

    @Override
    default void request() {

    }
}
