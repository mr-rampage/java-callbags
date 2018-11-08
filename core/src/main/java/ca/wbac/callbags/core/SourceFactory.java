package ca.wbac.callbags.core;

import java.util.function.Supplier;

public interface SourceFactory<T> extends Supplier<Source<T>> {
}
