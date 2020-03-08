package ca.wbac.callbags.basics;

public interface ICallbag<E> {
    default void terminate() {
    }

    default void terminate(E error) {
    }
}
