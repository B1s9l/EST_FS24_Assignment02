package zest;

import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class uniquePathsTest {

    private UniquePaths instance;
    @BeforeEach
    public void setUp(){
        instance = new UniquePaths();
    }
    @Test
    public void testBaseCaseNoActualPathInput(){
        Assertions.assertEquals(1, instance.uniquePaths(1,1));
    }
    @Test
    public void testSmallInput(){
        Assertions.assertEquals(3, instance.uniquePaths(2,3));
    }
    @Test
    public void testalmostOverflowInput(){
        Assertions.assertEquals(1166803110, instance.uniquePaths(18,17));
    }
    @Test
    public void testmToSmallInput(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->{instance.uniquePaths(0,1);});
    }
    @Test
    public void testnToSmallInput(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->{instance.uniquePaths(1,0);});
    }
    @Test
    public void testnToBigInput(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->{instance.uniquePaths(1,101);});
    }
    @Test
    public void testmToBigInput(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->{instance.uniquePaths(101,1);});
    }
    @Test
    public void testOverallInputOverflows(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->{instance.uniquePaths(18,18);});
    }
    @Test
    public void testlargerInputNoOverflow(){
        Assertions.assertEquals(28, instance.uniquePaths(3,7));
    }

    @Property
    void preConditionNotMetThrowsException(@ForAll @IntRange(min = Integer.MIN_VALUE, max = 0) int m1,
                                           @ForAll @IntRange(min = 101, max = Integer.MAX_VALUE) int m2,
                                           @ForAll @IntRange(min = Integer.MIN_VALUE, max = 0) int n1,
                                           @ForAll @IntRange(min = 101, max = Integer.MAX_VALUE) int n2,
                                           @ForAll @IntRange(min = 0,max = 3) int which){
        instance = new UniquePaths();
        int m = which < 2 ? (which == 0 ? m1 : m2 ) : 3;// to ensure both validations are checked and not just for m
        int n = which > 1 ? n2 : n1; // always checked after, therefor no need to adjust values, will be implicitly return bc of m being checked first
        assertThatThrownBy(() ->{
            instance.uniquePaths(m,n);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Property
    void overflowThrowsIllegalArgumentException(@ForAll @IntRange(min = 18, max = 100) int m){
        instance = new UniquePaths();
        int n = m > 69 ? 8 : m > 51 ? 9 : m > 41 ? 10 : m > 34 ? 11 : m > 29 ? 12: m > 26 ?13 : m > 23 ? 14: m > 21 ? 15 : m > 20 ? 16 : m > 18 ? 17 : 18;
        assertThatThrownBy(() ->{
            instance.uniquePaths(m,n);
        }).isInstanceOf(IllegalArgumentException.class);
    }
    @Property
    void noOverflowCalculatesCorrectResult(@ForAll @IntRange(min=1, max = 100) int m, @ForAll @IntRange(max=10)int k){
        instance = new UniquePaths();
        int n = m > 69 ? rand(1,7): m > 51 ? rand(1,8) : m > 41 ? rand(1,9) : m > 34 ? rand(1,10) : m > 29 ?rand(1,11): m > 26 ?rand(1,12) : m > 23 ?rand(1,13): m > 21 ? rand(1,14) : m > 20 ?rand(1,15) : m > 18 ? rand(1,16) :rand(1,17);
        int expected = numberOfPaths(m,n);
        assertThat(instance.uniquePaths(m,n)).isEqualTo(expected);
    }
    private int rand(int min, int max){
        return Arbitraries.integers().between(min, max).sample();
    }
    private int numberOfPaths(int m, int n)
    {
        long path = 1;
        for (int i = n; i < (m + n - 1); i++) {
            path *= i;
            path /= (i - n + 1);
        }
        return (int)path;
    }
}