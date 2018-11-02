package ca.wbac.callbags.core;

import java.util.function.Function;

public interface SinkConnector<T, K> extends Function<SourceInitiator<T>, SourceInitiator<K>> {
}
