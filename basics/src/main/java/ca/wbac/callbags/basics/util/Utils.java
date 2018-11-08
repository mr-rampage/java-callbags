package ca.wbac.callbags.basics.util;

import ca.wbac.callbags.core.Sink;
import ca.wbac.callbags.core.Source;
import ca.wbac.callbags.core.SourceFactory;

import java.util.function.Function;

public class Utils {

    public static <T, K> void pipe(SourceFactory<T> source,
                                   Function<Source<T>, Source<K>> operations,
                                   Sink<K> sink) {
        sink.accept(operations.apply(source.get()));
    }
}
