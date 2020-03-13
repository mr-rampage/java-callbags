package ca.wbac.callbags.basics.sink;

import java.util.function.Consumer;

public interface Sink {
    static <T> ca.wbac.callbags.core.Sink<T> forEach(final Consumer<T> consumer) {
        return new ForEach<>(consumer);
    }
}
