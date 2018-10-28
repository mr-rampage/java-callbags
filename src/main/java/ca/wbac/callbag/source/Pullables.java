package ca.wbac.callbag.source;

import ca.wbac.callbag.Callbag;

public final class Pullables {
    public static Callbag<Integer> range(final Integer lowerBound, final Integer upperBound) {
        return new Range(lowerBound, upperBound);
    }
}
