package ca.wbac.callbags.core;

public class Callbag<I, O> implements Sink<I>, Source<O> {
    @Override
    public void greet(Source<I> talkback) {

    }

    @Override
    public void deliver(I data) {

    }

    @Override
    public void greet(Sink<O> sink) {

    }

    @Override
    public void request() {

    }
}
