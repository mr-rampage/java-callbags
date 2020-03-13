package ca.wbac.callbags.core;

public abstract class AbstractOperator<I, O> implements Source<O>, Sink<I> {
    protected Source<I> source;
    protected Sink<O> sink;

    @Override
    public void greet(Source<I> source) {
        this.source = source;
    }

    @Override
    public void greet(Sink<O> sink) {
        this.sink = sink;
        sink.greet(this);
    }

    @Override
    public void request() {
        this.source.request();
    }

    @Override
    public void terminate() {
        sink.terminate();
    }

    @Override
    public void terminate(Throwable error) {
        sink.terminate(error);
    }
}
