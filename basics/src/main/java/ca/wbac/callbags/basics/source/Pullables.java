package ca.wbac.callbags.basics.source;

import ca.wbac.callbags.core.SourceInitiator;

public class Pullables {
    public static SourceInitiator<Integer> range(int lowerBound, int upperBound) {
        return new Range(lowerBound, upperBound);
    }
}
