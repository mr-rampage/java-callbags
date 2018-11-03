package ca.wbac.callbags.basics.util;

import ca.wbac.callbags.core.SinkTerminator;
import ca.wbac.callbags.core.SourceFactory;
import ca.wbac.callbags.core.SourceInitiator;

import java.util.function.Function;

public class Utils {

    public static <T, K> void pipe(SourceFactory<T> source, Function<SourceInitiator<T>, SourceInitiator<K>> pipeline, SinkTerminator<K> sink) {
        sink.accept(pipeline.apply(source.get()));
    }
}
