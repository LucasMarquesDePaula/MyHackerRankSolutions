/*
 * https://www.hackerrank.com/challenges/organizing-containers-of-balls/problem
 */
package algorithms.implementation;

import java.util.Scanner;

/**
 *
 * @author Lucas
 */
public class OrganizingContainersOfBalls {

    static String organizingContainers(int[][] container) {
        // Complete this function
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int a0 = 0; a0 < q; a0++) {
            int n = in.nextInt();
            int[][] container = new int[n][n];
            for (int container_i = 0; container_i < n; container_i++) {
                for (int container_j = 0; container_j < n; container_j++) {
                    container[container_i][container_j] = in.nextInt();
                }
            }
            String result = organizingContainers(container);
            System.out.println(result);
        }
        in.close();
    }
}
