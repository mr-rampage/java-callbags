package ca.wbac.callbags.basics.sink;

import ca.wbac.callbags.basics.ISource;

import java.util.function.Consumer;

public final class Sink {
    public static <T, E> Consumer<ISource<T, E>> forEach(final Consumer<T> consumer) {
        var sink = new ForEach<T, E>(consumer);
        return source -> source.greet(sink);
    }
}
