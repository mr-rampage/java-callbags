package ca.wbac.callbags.basics;

public class Callbag<I, O, E> implements ISink<I, E>, ISource<O, E> {
    @Override
    public void greet(ISource<I, E> talkback) {

    }

    @Override
    public void deliver(I data) {

    }

    @Override
    public void greet(ISink<O, E> sink) {

    }

    @Override
    public void request() {

    }
}
