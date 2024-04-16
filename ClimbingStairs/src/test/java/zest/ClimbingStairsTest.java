package zest;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class ClimbingStairsTest {

    ClimbingStairs obj = new ClimbingStairs();

    // Task 1 - code coverage

    @Test
    public void test_one (){
        Assertions.assertEquals(obj.climbStairs(1), 1);
    }
    @Test
    public void test_three(){
        Assertions.assertEquals(obj.climbStairs(3), 3);
    }

    // Task 3 testing contracts
    @Test
    public void test_two_pre(){
        Assertions.assertEquals(obj.climbStairs(2), 2);
    }

    @Test
    public void test_four_pre(){
        Assertions.assertEquals(obj.climbStairs(4), 5);
    }

    @Test
    public void test_91_pre(){
        assertThat(obj.climbStairs(91)).isLessThan(Long.MAX_VALUE);
    }

    @Test
    public void test_92_pre(){
        assertThatThrownBy(() -> {
            obj.climbStairs(92);
        }).isInstanceOf(RuntimeException.class).hasMessageStartingWith("One step before should have more possible ways");
    }

    @Test
    public void test_zero_pre() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            obj.climbStairs(0);
        });

        String expectedMessage = "Input must be a positive integer";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void test_negative_pre(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            obj.climbStairs(-1);
        });

        String expectedMessage = "Input must be a positive integer";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Property
    void less_or_equal_zero(
            @ForAll
            @IntRange(max = 0) int input
    ){
        assertThatThrownBy(() -> {
            obj.climbStairs(input);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Input must be a positive integer");
    }

    @Property
    void one_or_two(
            @ForAll
            @IntRange(min = 1, max = 2) int input
    ){
        long actual = obj.climbStairs(input);
        long expected = input;
        assertThat(actual).isEqualTo(expected);

    }

    @Property
    @Report(Reporting.GENERATED)
    void larger_input(
            @ForAll
            @IntRange(min = 3) int input
    ){
        long actual = obj.climbStairs(input);
        long[] memory = new long[input];
        Arrays.fill(memory, 0);
        long expected = mock_stairs_input_greater_equal_three(memory, input);
        assertThat(actual).isEqualTo(expected);
    }

    @Provide
    private long mock_stairs_input_greater_equal_three( long[] memory, int n ){
        if (memory[n-1] != 0){
            return memory[n-1];
        }
        if (n==1 || n ==2){
            memory[n-1] = n;
            return n;
        }
        memory[n-1] = mock_stairs_input_greater_equal_three(memory, n-1) + mock_stairs_input_greater_equal_three(memory, n-2);
        return memory[n-1];
    }


}


