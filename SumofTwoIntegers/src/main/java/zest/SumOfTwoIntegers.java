package zest;

public class SumOfTwoIntegers {
    public int getSum(int a, int b) {

        assert Integer.MIN_VALUE <= a && a <= Integer.MAX_VALUE : "a is out of range";
        assert Integer.MIN_VALUE <= b && b <= Integer.MAX_VALUE : "b is out of range";

        int A = a, B = b;
        int res;

        while (b != 0) {
            int carry = (a & b) << 1;  // Carry now contains common set bits of a and b
            a = a ^ b;  // Sum of bits of a and b where at least one of the bits is not set
            b = carry;  // Carry is shifted by one so that adding it to a gives the required sum
        }

        res = a;
        assert Integer.MIN_VALUE <= res && res <= Integer.MAX_VALUE : "res is not an integer";
        assert res == A + B : "sum is incorrect";

        return a;
    }
}
