package ca.wbac.callbags.basics.sink;

import ca.wbac.callbags.basics.ISink;

import java.util.function.Consumer;

public interface Sink {
    static <T> ISink<T> forEach(final Consumer<T> consumer) {
        return new ForEach<>(consumer);
    }
}
