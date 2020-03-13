package ca.wbac.callbags.basics;

public interface ISource<I> extends ICallbag {
    void greet(ISink<I> sink);

    default void request() {
    }

    default <O> AbstractOperator<I, O> pipe(AbstractOperator<I, O> operator) {
        this.greet(operator);
        return operator;
    }
}
