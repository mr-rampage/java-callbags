package ca.wbac.callbags.basics.operator;

import ca.wbac.callbags.core.Operator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import java.util.function.Predicate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

final class FilterTest extends AbstractOperatorTest<Integer, Integer> {
    @Spy
    private Predicate<Integer> predicate;
    @InjectMocks
    private Filter<Integer> fixture;

    @Test
    @DisplayName("should not deliver data when predicate is false")
    void testFalsyPredicate() {
        when(predicate.test(5)).thenReturn(false);
        sinkCaptor.getValue().deliver(5);
        verify(sink, never()).deliver(any(Integer.class));
    }

    @Test
    @DisplayName("should deliver data when predicate is true")
    void testTruthyPredicate() {
        when(predicate.test(5)).thenReturn(true);
        sinkCaptor.getValue().deliver(5);
        verify(sink).deliver(5);
    }

    @Override
    Operator<Integer, Integer> getFixture() {
        return fixture;
    }
}
