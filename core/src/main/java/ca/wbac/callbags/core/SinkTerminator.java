package ca.wbac.callbags.core;

import java.util.function.Consumer;

public interface SinkTerminator<T> extends Consumer<SourceInitiator<T>> {
}
