package ca.wbac.callbags.core;

public interface Source<I> extends Terminate {
    void greet(Sink<I> sink);

    default void request() {
    }

    default <O> AbstractOperator<I, O> pipe(AbstractOperator<I, O> operator) {
        this.greet(operator);
        return operator;
    }

    default void pipe(Sink<I> sink) {
        this.greet(sink);
    }
}
