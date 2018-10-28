package ca.wbac.callbag.source;

public final class Pullables {
    public static Range range(final Integer lowerBound, final Integer upperBound) {
        return new Range(lowerBound, upperBound);
    }
}
