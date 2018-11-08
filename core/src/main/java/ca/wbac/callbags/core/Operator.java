package ca.wbac.callbags.core;

import java.util.function.Function;

public interface Operator<I, O> extends Function<Source<I>, Source<O>> {
}
