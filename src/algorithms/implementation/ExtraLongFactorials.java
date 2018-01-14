/*
 * https://www.hackerrank.com/challenges/extra-long-factorials/problem
 */
package algorithms.implementation;

import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Lucas
 */
public class ExtraLongFactorials {

    static BigInteger extraLongFactorials(BigInteger n) {
        if (n.compareTo(BigInteger.ONE) <= 0) {
            return BigInteger.ONE;
        }
        
        return n.multiply(extraLongFactorials(n.subtract(BigInteger.ONE)));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        long n = in.nextLong();
        BigInteger factorial = extraLongFactorials(BigInteger.valueOf(n));
        System.out.println(factorial.toString());
        
        in.close();
    }
}
