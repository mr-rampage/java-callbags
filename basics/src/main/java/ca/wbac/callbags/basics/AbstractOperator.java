package ca.wbac.callbags.basics;

public abstract class AbstractOperator<I, O> implements ISource<O>, ISink<I> {
    protected ISource<I> source;
    protected ISink<O> sink;

    @Override
    public void greet(ISource<I> source) {
        this.source = source;
    }

    @Override
    public void greet(ISink<O> sink) {
        this.sink = sink;
        sink.greet(this);
    }

    @Override
    public void request() {
        this.source.request();
    }

    public void pipe(ISink<O> sink) {
        this.greet(sink);
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
