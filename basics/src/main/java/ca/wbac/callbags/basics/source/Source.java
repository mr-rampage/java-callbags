package ca.wbac.callbags.basics.source;

public final class Source {
    public static Range range(final int lowerBound, final int upperBound) {
        return new Range(lowerBound, upperBound);
    }
}
