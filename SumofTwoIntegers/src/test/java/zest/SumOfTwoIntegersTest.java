package zest;

import net.jqwik.api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import net.jqwik.api.constraints.IntRange;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SumOfTwoIntegersTest {

    private SumOfTwoIntegers instance = new SumOfTwoIntegers();

    //TEST 1
    @Test
    public void testBothPositive() {
        SumOfTwoIntegers calculator = new SumOfTwoIntegers();
        assertEquals(5, calculator.getSum(2, 3));
    }

    //TEST 2
    @Test
    public void testBothNegative() {
        SumOfTwoIntegers calculator = new SumOfTwoIntegers();
        assertEquals(-5, calculator.getSum(-2, -3));
    }

    //TEST 3
    @Test
    public void testMixedSigns() {
        SumOfTwoIntegers calculator = new SumOfTwoIntegers();
        assertEquals(1, calculator.getSum(-2, 3));
        assertEquals(-1, calculator.getSum(2, -3));
    }

    //TEST 4
    @Test
    public void testWithZero() {
        SumOfTwoIntegers calculator = new SumOfTwoIntegers();
        assertEquals(2, calculator.getSum(2, 0));
        assertEquals(-3, calculator.getSum(0, -3));
        assertEquals(0, calculator.getSum(0, 0));
    }

    //TEST 5
    @Test
    public void testMaxBoundaryValues() {
        SumOfTwoIntegers calculator = new SumOfTwoIntegers();
        assertEquals(Integer.MAX_VALUE, calculator.getSum(Integer.MAX_VALUE, 0));
    }

    @Test
    public void testMaxBoundaryValuesFail() {
        SumOfTwoIntegers calculator = new SumOfTwoIntegers();
        assertEquals(Integer.MIN_VALUE, calculator.getSum(Integer.MAX_VALUE, 1));
    }

    //TEST 6
    @Test
    public void testMinBoundaryValues() {
        SumOfTwoIntegers calculator = new SumOfTwoIntegers();
        assertEquals(Integer.MIN_VALUE, calculator.getSum(Integer.MIN_VALUE, 0));
    }

    //TASK 2/3

    //TEST 7
    @Test
    public void testSumValidInputs() {
        SumOfTwoIntegers calculator = new SumOfTwoIntegers();
        assertEquals(3, calculator.getSum(1, 2));
    }

    //TEST 8
    @Test
    public void testInvariantPreservation() {
        SumOfTwoIntegers calculator = new SumOfTwoIntegers();
        int a = 20, b = 30;
        calculator.getSum(a, b);
        assertEquals(20, a);
        assertEquals(30, b);
    }

    //TEST 9
    @Test
    public void testCorrectCalculationPostCondition() {
        SumOfTwoIntegers calculator = new SumOfTwoIntegers();
        int result = calculator.getSum(-2, 3);
        assertEquals(1, result);
    }

    //PROPERTY TEST
    @Property
    public void testAnyIntegers(
            @ForAll @IntRange() int valA,
            @ForAll @IntRange() int valB

    ){
        instance = new SumOfTwoIntegers();
        int expected = valA + valB;
        assertThat(instance.getSum(valA, valB)).isEqualTo(expected);

    }

}
