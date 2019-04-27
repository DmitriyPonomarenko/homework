package part1.lesson02.task02;

import part1.lesson02.task02.customExceptions.NegativeNumberException;

/**
 * пример на if
 */
public class Main {

    private static final int N = 1000;

    public static void main(String[] args) {
        int[] numbers = new int[N];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * N) - N / 2;
        }
        for (int k : numbers) {
            try {
                if (k < 0) {
                    throw new NegativeNumberException(k + " < 0");
                }
            } catch (NegativeNumberException e) {
                System.out.println(e.getMessage());
                continue;
            }
            double q = Math.sqrt(k);
            int floor = (int) q;
            if (floor * floor == k) {
                System.out.println(k + " является квадратом целого числа");
            }
        }
    }
}
