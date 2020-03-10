package ca.wbac.callbags.basics;

public interface ICallbag {
    default void terminate() {
    }

    default void terminate(Throwable error) {
    }
}
