/*
 * https://www.hackerrank.com/challenges/non-divisible-subset/problem
 */
package algorithms.implementation;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Lucas
 */
public class NondivisibleSubset {

    static int nonDivisibleSubset(int k, int[] arr) {

        int[] remainders = new int[k];
        Arrays.fill(remainders, 0);

        for (int i : arr) {
            remainders[remain]++;
        }

        int count = 0;

        for (int i = 1, length = remainders.length, max = length / 2 + 1; i < max; i++) {
            if (i != length - i) {
                count += Math.max(remainders[i], remainders[length - i]);
            }
        }

        // If there are more then 1 value wich remainder's is 0, add 1 to counting 
        count += Math.min(remainders[0], 1);

        if (remainders.length % 2 == 0) {
            count += Math.min(remainders[remainders.length / 2], 1);
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int arr_i = 0; arr_i < n; arr_i++) {
            arr[arr_i] = in.nextInt();
        }
        int result = nonDivisibleSubset(k, arr);
        System.out.println(result);
        in.close();
    }
}
