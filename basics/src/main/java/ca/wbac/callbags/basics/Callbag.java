package ca.wbac.callbags.basics;

public class Callbag<I, O> implements ISink<I>, ISource<O> {
    @Override
    public void greet(ISource<I> talkback) {

    }

    @Override
    public void deliver(I data) {

    }

    @Override
    public void greet(ISink<O> sink) {

    }

    @Override
    public void request() {

    }
}
